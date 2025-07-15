package net.kamiland.ultimatehub.data.impl.player;

import net.kamiland.ultimatehub.data.manager.cp.ConnectionPoolManager;
import net.kamiland.ultimatehub.data.manager.player.PlayerDataManager;
import net.kamiland.ultimatehub.data.model.player.PlayerData;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.sql.SQLException;
import java.util.UUID;

public class MySQLPlayerDataManager implements PlayerDataManager {

    private final Logger logger;
    private final ConnectionPoolManager cpManager;
    private final String tablePrefix;

    public MySQLPlayerDataManager(Logger logger, ConfigManager configManager, ConnectionPoolManager cpManager) {
        this.logger = logger;
        this.cpManager = cpManager;
        this.tablePrefix = configManager.getPluginConfig().DATASOURCE_TABLE_PREFIX;

        String testQuery = "SELECT 1;";
        try (var conn = cpManager.getConnection();
             var stmt = conn.createStatement()) {
            try {
                stmt.executeQuery(testQuery);
                conn.commit();
            } catch (SQLException e) {
                putError(e);
                conn.rollback();
            }
        } catch (SQLException e) {
            putError(e);
        }

        String createTable = String.format(
                "CREATE TABLE IF NOT EXISTS %splayerdata (uuid VARCHAR(36) PRIMARY KEY, name VARCHAR(16) NOT NULL, login_times INTEGER NOT NULL DEFAULT 0, agreement BOOLEAN NOT NULL DEFAULT 0);",
                tablePrefix);
        try (var conn = cpManager.getConnection();
             var stmt = conn.createStatement()) {
            try {
                stmt.executeUpdate(createTable);
                conn.commit();
            } catch (SQLException e) {
                putError(e);
                conn.rollback();
            }
        } catch (SQLException e) {
            putError(e);
        }
    }

    @Override
    public void savePlayer(UUID uuid, PlayerData playerData) {
        String sql = String.format(
                "UPDATE %splayerdata SET name=?, login_times=?, agreement=? WHERE uuid=?;",
                tablePrefix);
        try (var conn = cpManager.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            try {
                stmt.setString(1, playerData.getName());
                stmt.setInt(2, playerData.getLoginTimes());
                stmt.setBoolean(3, playerData.isAgreement());
                stmt.setString(4, uuid.toString());
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                putError(e);
                conn.rollback();
            }
        } catch (SQLException e) {
            putError(e);
        }
    }

    @Override
    public void putNewPlayer(UUID uuid, String name) {
        String sql = String.format("INSERT INTO %splayerdata (uuid, name) VALUES (?, ?);", tablePrefix);
        try (var conn = cpManager.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            try {
                stmt.setString(1, uuid.toString());
                stmt.setString(2, name);
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                putError(e);
                conn.rollback();
            }
        } catch (SQLException e) {
            putError(e);
        }
    }

    @Override
    public void removePlayer(UUID uuid) {
        String sql = String.format("DELETE FROM %splayerdata WHERE uuid=?;", tablePrefix);
        try (var conn = cpManager.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            try {
                stmt.setString(1, uuid.toString());
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                putError(e);
                conn.rollback();
            }
        } catch (SQLException e) {
            putError(e);
        }
    }

    @Override
    public void loadPlayer(UUID uuid) {

    }

    @Override
    public void clearAllAgreementStatus() {
        String sql = String.format(
                "UPDATE %splayerdata SET agreement=? WHERE agreement=?;",
                tablePrefix);
        try (var conn = cpManager.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            try {
                stmt.setBoolean(1, false);
                stmt.setBoolean(2, true);
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                putError(e);
                conn.rollback();
            }
        } catch (SQLException e) {
            putError(e);
        }
    }

    @Override
    public @Nullable PlayerData getPlayerData(UUID uuid) {
        String sql = String.format("SELECT * FROM %splayerdata WHERE uuid=?;", tablePrefix);
        try (var conn = cpManager.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            try {
                stmt.setString(1, uuid.toString());
                try (var rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        PlayerData playerData = new PlayerData(uuid);
                        playerData.setName(rs.getString("name"));
                        playerData.setLoginTimes(rs.getInt("login_times"));
                        playerData.setAgreement(rs.getBoolean("agreement"));
                        conn.commit();
                        return playerData;
                    }
                    conn.commit();
                }
            } catch (SQLException e) {
                putError(e);
                conn.rollback();
            }
        } catch (SQLException e) {
            putError(e);
        }
        return null;
    }

    @Override
    public @Nullable UUID getPlayerUniqueIdByName(String name) {
        String sql = String.format("SELECT uuid FROM %splayerdata WHERE name=?;", tablePrefix);
        try (var conn = cpManager.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            try {
                stmt.setString(1, name);
                try (var rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        UUID uuid = UUID.fromString(rs.getString("uuid"));
                        conn.commit();
                        return uuid;
                    }
                    conn.commit();
                }
            } catch (SQLException e) {
                putError(e);
                conn.rollback();
            }
        } catch (SQLException e) {
            putError(e);
        }
        return null;
    }

    @Override
    public boolean isPlayerExist(UUID uuid) {
        String sql = String.format("SELECT 1 FROM %splayerdata WHERE uuid=?;", tablePrefix);
        try (var conn = cpManager.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            try {
                stmt.setString(1, uuid.toString());
                try (var rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        conn.commit();
                        return true;
                    }
                    conn.commit();
                }
            } catch (SQLException e) {
                putError(e);
                conn.rollback();
            }
        } catch (SQLException e) {
            putError(e);
        }
        return false;
    }

    private void putError(SQLException e) {
        logger.error("Error while executing SQL statement: {}", e.getMessage());
    }

}
