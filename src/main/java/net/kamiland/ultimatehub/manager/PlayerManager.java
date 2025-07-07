package net.kamiland.ultimatehub.manager;

import net.kamiland.ultimatehub.UltimateHub;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Central player caching system handling player data synchronization
 *
 * <p>Maintains three parallel data structures for efficient player lookups:
 * <ul>
 *   <li>List collection for ordered iteration</li>
 *   <li>UUID mapping for unique identifier based access</li>
 *   <li>Name mapping for direct name resolution (case-sensitive)</li>
 * </ul>
 *
 * @author while1cry
 */
public class PlayerManager implements Listener {

    private final UltimateHub plugin;
    private final List<Player> players = new ArrayList<>();
    private final Map<UUID, Player> uuidPlayerMap = new HashMap<>();
    private final Map<String, Player> namePlayerMap = new HashMap<>();

    /**
     * Initializes player manager and registers event listeners
     * @param plugin Main plugin instance for Bukkit API access
     */
    public PlayerManager(UltimateHub plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Loads all online players into caching system
     */
    public void loadAll() {
        plugin.getServer().getOnlinePlayers().forEach(this::load);
    }

    /**
     * Adds individual player to caching system
     * @param player Player instance to cache
     */
    public void load(Player player) {
        if (players.contains(player))
            return;
        players.add(player);
        uuidPlayerMap.put(player.getUniqueId(), player);
        namePlayerMap.put(player.getName(), player);
    }

    /**
     * Removes all online players from cache
     */
    public void unloadAll() {
        plugin.getServer().getOnlinePlayers().forEach(this::unload);
    }

    /**
     * Removes specific player from caching system
     * @param player Player instance to remove
     */
    public void unload(Player player) {
        players.remove(player);
        uuidPlayerMap.remove(player.getUniqueId());
        namePlayerMap.remove(player.getName());
    }

    /**
     * Retrieves player by UUID
     * @param uuid Minecraft UUID for lookup
     * @return Player instance or null if not cached
     */
    @Nullable
    public Player getPlayer(UUID uuid) {
        return uuidPlayerMap.get(uuid);
    }

    /**
     * Retrieves player by exact name match
     * @param name Case-sensitive player name
     * @return Player instance or null if not found
     */
    @Nullable
    public Player getPlayer(String name) {
        return namePlayerMap.get(name);
    }

    /**
     * Handles player join events (lowest priority)
     * @param event Join event containing new player
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        load(event.getPlayer());
    }

    /**
     * Handles player quit events (lowest priority)
     * @param event Quit event containing leaving player
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        unload(event.getPlayer());
    }

}
