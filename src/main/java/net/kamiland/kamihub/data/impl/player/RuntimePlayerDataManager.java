package net.kamiland.kamihub.data.impl.player;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.data.manager.player.PlayerDataManager;
import net.kamiland.kamihub.data.model.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Handles player data during their online session. Provides a fast-access, in-memory cache
 * that synchronizes with persistent storage (e.g. MySQL).
 */
public class RuntimePlayerDataManager implements PlayerDataManager, Listener {

    private final KamiHub plugin;
    private final PlayerDataManager storagePDM;
    private final Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    /**
     * Initializes the runtime manager and preloads data for currently online players.
     */
    public RuntimePlayerDataManager(KamiHub plugin) {
        this.plugin = plugin;
        this.storagePDM = new MySQLPlayerDataManager(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        Bukkit.getScheduler().runTaskAsynchronously(plugin, this::loadOnlinePlayers);
    }

    /**
     * Handles pre-login event to load or create player data.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        if (isPlayerExistFromStorage(event.getUniqueId()))
            loadPlayer(event.getUniqueId());
        else
            putNewPlayer(event.getUniqueId(), event.getName());
    }

    /**
     * Unloads player data when the player quits.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            if (! event.getPlayer().isOnline())
                playerDataMap.remove(event.getPlayer().getUniqueId());
        }, 600L);
    }

    /**
     * Loads player data from storage into memory.
     */
    @Override
    public void loadPlayer(UUID uuid) {
        PlayerData data = getPlayerDataFromStorage(uuid);
        if (data != null) {
            playerDataMap.put(uuid, data);
        }
    }

    /**
     * Saves player data to memory (cache only).
     */
    @Override
    public void savePlayer(UUID uuid, PlayerData playerData) {
        playerDataMap.put(uuid, playerData);
    }

    /**
     * Persists cached player data to storage.
     */
    public void savePlayerToStorage(UUID uuid) {
        storagePDM.savePlayer(uuid, playerDataMap.get(uuid));
    }

    /**
     * Persists specified player data directly to storage.
     */
    public void savePlayerToStorage(UUID uuid, PlayerData playerData) {
        storagePDM.savePlayer(uuid, playerData);
    }

    /**
     * Creates a new player record in storage and loads it.
     */
    @Override
    public void putNewPlayer(UUID uuid, String name) {
        storagePDM.putNewPlayer(uuid, name);
        loadPlayer(uuid);
    }

    /**
     * Removes player data from both memory and storage.
     */
    @Override
    public void removePlayer(UUID uuid) {
        playerDataMap.remove(uuid);
        storagePDM.removePlayer(uuid);
    }

    /**
     * Clears agreement status for all cached players.
     */
    @Override
    public void clearAllAgreementStatus() {
        playerDataMap.values().stream()
                .filter(PlayerData::isAgreement)
                .forEach(p -> p.setAgreement(false));
    }

    /**
     * Clears agreement status for all players in storage.
     */
    public void clearAllAgreementStatusFromStorage() {
        storagePDM.clearAllAgreementStatus();
    }

    /**
     * Gets cached player data by UUID.
     */
    @Override
    public @Nullable PlayerData getPlayerData(UUID uuid) {
        return playerDataMap.get(uuid);
    }

    /**
     * Gets player data from persistent storage by UUID.
     */
    public @Nullable PlayerData getPlayerDataFromStorage(UUID uuid) {
        return storagePDM.getPlayerData(uuid);
    }

    /**
     * Gets player UUID from cache by player name.
     */
    @Override
    public @Nullable UUID getPlayerUniqueIdByName(String name) {
        try {
            return playerDataMap.entrySet().stream()
                    .filter(entry -> entry.getValue().getName().equals(name))
                    .findFirst()
                    .orElseThrow()
                    .getKey();
        } catch (NoSuchElementException ignore) {
            return null;
        }
    }

    /**
     * Gets player UUID from storage by player name.
     */
    public @Nullable UUID getPlayerUniqueIdByNameFromStorage(String name) {
        return storagePDM.getPlayerUniqueIdByName(name);
    }

    /**
     * Checks if player data exists in memory.
     */
    @Override
    public boolean isPlayerExist(UUID uuid) {
        return playerDataMap.containsKey(uuid);
    }

    /**
     * Checks if player data exists in persistent storage.
     */
    public boolean isPlayerExistFromStorage(UUID uuid) {
        return storagePDM.isPlayerExist(uuid);
    }

    /**
     * Loads data for all currently online players.
     */
    public void loadOnlinePlayers() {
        plugin.getServer().getOnlinePlayers().forEach(p -> loadPlayer(p.getUniqueId()));
    }

    /**
     * Unloads all online players' data from memory.
     */
    public void unloadAllPlayers() {
        plugin.getServer().getOnlinePlayers().forEach(p -> unloadPlayer(p.getUniqueId()));
    }

    /**
     * Unloads a single player's data from memory.
     */
    public void unloadPlayer(UUID uuid) {
        playerDataMap.remove(uuid);
    }

    /**
     * Closes the runtime manager and persists all data to storage.
     */
    public void close() {
        playerDataMap.clear();
        HandlerList.unregisterAll(this);
    }

}
