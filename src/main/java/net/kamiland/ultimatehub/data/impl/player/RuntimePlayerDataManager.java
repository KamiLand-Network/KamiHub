package net.kamiland.ultimatehub.data.impl.player;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.data.manager.cp.ConnectionPoolManager;
import net.kamiland.ultimatehub.data.manager.player.PlayerDataManager;
import net.kamiland.ultimatehub.data.model.player.PlayerData;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * RuntimePlayerDataManager is an in-memory manager for player data during a player's online session.
 * It serves as a bridge between the runtime environment and the persistent storage implementation,
 * enabling efficient access and modification of player data while they are online.
 * <p>
 * Responsibilities:<br>
 * - Load and cache player data into memory upon login.<br>
 * - Provide fast access to Player and PlayerData instances.<br>
 * - Persist updated player data to storage.<br>
 * - Clean up data upon player logout.<br>
 * - Support managing data through UUIDs, player names, and Player instances.
 * </p>
 */
public class RuntimePlayerDataManager implements PlayerDataManager, Listener {

    private final UltimateHub plugin;
    private final PlayerDataManager storagePDM;
    private final Map<UUID, Player> uuidPlayerMap = new HashMap<>();
    private final Map<String, Player> namePlayerMap = new HashMap<>();
    private final Map<Player, PlayerData> playerDataMap = new HashMap<>();

    /**
     * Constructor that initializes the in-memory manager and registers event listeners.
     *
     * @param plugin         UltimateHub plugin instance
     * @param configManager  Configuration manager
     * @param cpManager      SQL connection pool manager
     */
    public RuntimePlayerDataManager(UltimateHub plugin, ConfigManager configManager, ConnectionPoolManager cpManager) {
        this.plugin = plugin;
        this.storagePDM = new MySQLPlayerDataManager(plugin.getSLF4JLogger(), configManager, cpManager);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Get the Player instance by UUID from memory.
     */
    public @Nullable Player getPlayer(UUID uuid) {
        return uuidPlayerMap.get(uuid);
    }

    /**
     * Get the Player instance by name from memory.
     */
    public @Nullable Player getPlayer(String name) {
        return namePlayerMap.get(name);
    }

    /**
     * Event listener to handle player joining. Loads player data.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            loadPlayer(event.getPlayer());
            PlayerData playerData = playerDataMap.get(event.getPlayer());
            if (playerData != null) {
                playerData.setLoginTimes(playerData.getLoginTimes() + 1);
                savePlayer(event.getPlayer(), playerData);
                savePlayerToStorage(event.getPlayer());
            }
        });
    }

    /**
     * Event listener to handle player quitting. Unloads player data.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        unloadPlayer(event.getPlayer());
    }

    /**
     * Load player data from storage and cache in memory.
     */
    @Override
    public void loadPlayer(Player player) {
        if (getPlayerDataFromStorage(player) != null) {
            uuidPlayerMap.put(player.getUniqueId(), player);
            namePlayerMap.put(player.getName(), player);
            playerDataMap.put(player, getPlayerDataFromStorage(player));
        } else {
            putNewPlayer(player);
        }
    }

    /**
     * Save (cache) player data to memory.
     */
    @Override
    public void savePlayer(Player player, PlayerData playerData) {
        playerDataMap.put(player, playerData);
    }

    /**
     * Save cached player data to persistent storage.
     */
    public void savePlayerToStorage(Player player) {
        storagePDM.savePlayer(player, playerDataMap.get(player));
    }

    /**
     * Save specified player data to persistent storage.
     */
    public void savePlayerToStorage(Player player, PlayerData playerData) {
        storagePDM.savePlayer(player, playerData);
    }

    /**
     * Add new player data to storage and load into memory.
     */
    @Override
    public void putNewPlayer(Player player) {
        storagePDM.putNewPlayer(player);
        loadPlayer(player);
    }

    /**
     * Remove player data from both memory and storage.
     */
    @Override
    public void removePlayer(Player player) {
        uuidPlayerMap.remove(player.getUniqueId());
        namePlayerMap.remove(player.getName());
        playerDataMap.remove(player);
        storagePDM.removePlayer(player);
    }

    /**
     * Get player data from memory.
     */
    @Override
    public @Nullable PlayerData getPlayerData(Player player) {
        return playerDataMap.get(player);
    }

    /**
     * Get player data from storage.
     */
    public @Nullable PlayerData getPlayerDataFromStorage(Player player) {
        return storagePDM.getPlayerData(player);
    }

    /**
     * Get player data by UUID from memory.
     */
    @Override
    public @Nullable PlayerData getPlayerData(UUID uuid) {
        return playerDataMap.get(uuidPlayerMap.get(uuid));
    }

    /**
     * Get player data by UUID from storage.
     */
    public @Nullable PlayerData getPlayerDataFromStorage(UUID uuid) {
        return storagePDM.getPlayerData(uuid);
    }

    /**
     * Get player data by name from memory.
     */
    @Override
    public @Nullable PlayerData getPlayerData(String name) {
        return playerDataMap.get(getPlayer(name));
    }

    /**
     * Get player data by name from storage.
     */
    public @Nullable PlayerData getPlayerDataFromStorage(String name) {
        return storagePDM.getPlayerData(name);
    }

    /**
     * Load all currently online players' data into memory.
     */
    public void loadOnlinePlayers() {
        plugin.getServer().getOnlinePlayers().forEach(this::loadPlayer);
    }

    /**
     * Unload all online players' data from memory.
     * Note: This does not automatically save the data. Call savePlayerToStorage() manually if needed.
     */
    public void unloadAllPlayers() {
        plugin.getServer().getOnlinePlayers().forEach(this::unloadPlayer);
    }

    /**
     * Unload a single player's data from memory.
     * Note: This does not automatically save the data. Call savePlayerToStorage() manually if needed.
     */
    public void unloadPlayer(Player player) {
        uuidPlayerMap.remove(player.getUniqueId());
        namePlayerMap.remove(player.getName());
        playerDataMap.remove(player);
    }

}
