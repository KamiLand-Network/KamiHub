package net.kamiland.ultimatehub.data.manager.player;

import net.kamiland.ultimatehub.data.model.player.PlayerData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Central interface for managing player data persistence and retrieval.
 * Provides unified access to player data across different storage implementations.
 *
 * <p>Handles core player data operations including:
 * <ul>
 *   <li>Loading/Saving player data from persistent storage</li>
 *   <li>Caching player data for runtime access</li>
 *   <li>Managing data lifecycle events</li>
 * </ul>
 *
 * @author while1cry
 */
public interface PlayerDataManager {

    /**
     * Loads player data into memory from storage.
     * @param player Target player to load data for
     */
    void loadPlayer(Player player);

    /**
     * Persists player data to storage.
     * @param player Target player to save data for
     */
    void savePlayer(Player player, PlayerData playerData);

    /**
     * Creates and caches new player data entry.
     * @param player New player to initialize data for
     */
    void putNewPlayer(Player player);

    /**
     * Removes player data from memory cache.
     * @param player Player to remove from cache
     */
    void removePlayer(Player player);

    /**
     * Retrieves cached player data by Player instance.
     * @param player Player to get data for
     * @return PlayerData instance or null if not cached
     */
    @Nullable
    PlayerData getPlayerData(Player player);

    /**
     * Retrieves cached player data by UUID.
     * @param uuid Unique player identifier
     * @return PlayerData instance or null if not found
     */
    @Nullable
    PlayerData getPlayerData(UUID uuid);

    /**
     * Retrieves cached player data by exact name match.
     * @param name Case-sensitive player name
     * @return PlayerData instance or null if not found
     */
    @Nullable
    PlayerData getPlayerData(String name);

}
