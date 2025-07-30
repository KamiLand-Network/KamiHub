package net.kamiland.kamihub.config;

import net.kamiland.kamihub.KamiHub;

public class PluginConfig extends Config {

    public Version CONFIG_VERSION;
    public boolean CHECK_FOR_UPDATES;
    public String DATASOURCE_STORAGE;
    public String DATASOURCE_MYSQL_JDBC_URL;
    public String DATASOURCE_MYSQL_USERNAME;
    public String DATASOURCE_MYSQL_PASSWORD;
    public String DATASOURCE_TABLE_PREFIX;
    public String DATASOURCE_H2_JDBC_URL;
    public String DATASOURCE_H2_USERNAME;
    public String DATASOURCE_H2_PASSWORD;
    public int CP_MAX_POOL_SIZE;
    public int CP_MIN_IDLE;
    public long CP_CONNECTION_TIMEOUT;
    public long CP_IDLE_TIMEOUT;
    public long CP_MAX_LIFETIME;
    public long CP_VALIDATION_TIMEOUT;
    public long CP_LEAK_DETECTION_THRESHOLD;
    public boolean CP_AUTO_COMMIT;

    public PluginConfig(KamiHub plugin) {
        super(plugin, "config.yml");
    }

    @Override
    public void load() {
        super.load();

        CONFIG_VERSION = new Version(config.getString("config-version", "0.0.0"));
        CHECK_FOR_UPDATES = config.getBoolean("check-for-updates");

        // Data Source
        DATASOURCE_STORAGE = config.getString("datasource.storage");
        DATASOURCE_MYSQL_JDBC_URL = "jdbc:mysql://" +
                config.getString("datasource.mysql.host") + ":" +
                config.getInt("datasource.mysql.port") + "/" +
                config.getString("datasource.mysql.database") +
                config.getString("datasource.mysql.params");
        DATASOURCE_MYSQL_USERNAME = config.getString("datasource.mysql.username");
        DATASOURCE_MYSQL_PASSWORD = config.getString("datasource.mysql.password");
        DATASOURCE_TABLE_PREFIX = config.getString("datasource.table-prefix");
        DATASOURCE_H2_JDBC_URL = "jdbc:h2:file:" +
                config.getString("datasource.h2.file") +
                config.getString("datasource.h2.params");
        DATASOURCE_H2_USERNAME = config.getString("datasource.h2.username");
        DATASOURCE_H2_PASSWORD = config.getString("datasource.h2.password");

        // Connection Pool
        CP_MAX_POOL_SIZE = config.getInt("datasource.connection-pool.maximum-pool-size");
        CP_MIN_IDLE = config.getInt("datasource.connection-pool.minimum-idle");
        CP_CONNECTION_TIMEOUT = config.getLong("datasource.connection-pool.connection-timeout");
        CP_IDLE_TIMEOUT = config.getLong("datasource.connection-pool.idle-timeout");
        CP_MAX_LIFETIME = config.getLong("datasource.connection-pool.max-lifetime");
        CP_VALIDATION_TIMEOUT = config.getLong("datasource.connection-pool.validation-timeout");
        CP_LEAK_DETECTION_THRESHOLD = config.getLong("datasource.connection-pool.leak-detection-threshold");
        CP_AUTO_COMMIT = config.getBoolean("datasource.connection-pool.auto-commit");
    }

    public void updateConfigComplete() {
        config.set("config-version", "1.1");
        save();
        CONFIG_VERSION = new Version(config.getString("config-version", "0.0.0"));
    }

}
