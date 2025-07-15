package net.kamiland.ultimatehub.data.manager.player;

import net.kamiland.ultimatehub.data.model.player.PlayerData;
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
     * Loads player data into memory.
     * @param uuid Target player to load data for
     */
    void loadPlayer(UUID uuid);

    /**
     * Persists player data to storage.
     * @param uuid Unique identifier of the target player
     * @param playerData Player data object to be saved
     */
    void savePlayer(UUID uuid, PlayerData playerData);

    /**
     * Creates new player data entry in the system.
     * @param uuid Unique identifier for the new player
     * @param name Player's display name
     */
    void putNewPlayer(UUID uuid, String name);

    /**
     * Removes player data from cache.
     * @param uuid Player to remove from cache
     */
    void removePlayer(UUID uuid);

    /**
     * Resets agreement status for all registered players.
     * <p>This operation will set agreement status to false in persistent storage.
     */
    void clearAllAgreementStatus();

    /**
     * Retrieves cached player data by UUID.
     * @param uuid Unique player identifier
     * @return PlayerData instance if found, null otherwise
     */
    @Nullable
    PlayerData getPlayerData(UUID uuid);

    /**
     * Looks up player's UUID by their display name.
     * @param name Player's display name
     * @return Associated UUID if exists, null if not found
     */
    @Nullable
    UUID getPlayerUniqueIdByName(String name);

    /**
     * Checks if player data exists for the specified UUID
     * <p>Verifies both in-memory cache and persistent storage</p>
     *
     * @param uuid Unique player identifier
     * @return true if the player data exists, false otherwise
     */
    boolean isPlayerExist(UUID uuid);

}
