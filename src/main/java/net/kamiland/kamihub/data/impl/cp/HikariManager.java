package net.kamiland.kamihub.data.impl.cp;

import com.zaxxer.hikari.HikariDataSource;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.data.manager.cp.ConnectionPoolManager;
import net.kamiland.kamihub.manager.ConfigManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

public class HikariManager implements ConnectionPoolManager {

    private final KamiHub plugin;
    private final ConfigManager configManager;
    private final HikariDataSource dataSource;

    public HikariManager(KamiHub plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.dataSource = new HikariDataSource();
    }

    @Override
    public void init() {
        dataSource.setPoolName("UHPool");
        switch (configManager.getPluginConfig().DATASOURCE_STORAGE.toLowerCase(Locale.ROOT)) {
            case "mysql" -> {
                dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                dataSource.setJdbcUrl(configManager.getPluginConfig().DATASOURCE_MYSQL_JDBC_URL);
                dataSource.setUsername(configManager.getPluginConfig().DATASOURCE_MYSQL_USERNAME);
                dataSource.setPassword(configManager.getPluginConfig().DATASOURCE_MYSQL_PASSWORD);
            }
            default -> {
                dataSource.setDriverClassName("org.h2.Driver");
                dataSource.setJdbcUrl(configManager.getPluginConfig().DATASOURCE_H2_JDBC_URL);
                dataSource.setUsername(configManager.getPluginConfig().DATASOURCE_H2_USERNAME);
                dataSource.setPassword(configManager.getPluginConfig().DATASOURCE_H2_PASSWORD);
            }
        }

        dataSource.setMaximumPoolSize(configManager.getPluginConfig().CP_MAX_POOL_SIZE);
        dataSource.setMinimumIdle(configManager.getPluginConfig().CP_MIN_IDLE);
        dataSource.setConnectionTimeout(configManager.getPluginConfig().CP_CONNECTION_TIMEOUT);
        dataSource.setIdleTimeout(configManager.getPluginConfig().CP_IDLE_TIMEOUT);
        dataSource.setMaxLifetime(configManager.getPluginConfig().CP_MAX_LIFETIME);
        dataSource.setValidationTimeout(configManager.getPluginConfig().CP_VALIDATION_TIMEOUT);
        dataSource.setAutoCommit(configManager.getPluginConfig().CP_AUTO_COMMIT);
    }

    @Override
    public void close() {
        dataSource.close();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
