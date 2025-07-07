package net.kamiland.ultimatehub.manager;

import lombok.Getter;
import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.config.PluginConfig;
import net.kamiland.ultimatehub.config.SpawnConfig;
import net.kamiland.ultimatehub.config.impl.PluginConfigImpl;
import net.kamiland.ultimatehub.config.impl.SpawnConfigImpl;
import org.slf4j.Logger;

/**
 * Central configuration handler managing plugin configuration files
 *
 * <p>Responsible for:
 * <ul>
 *   <li>Loading/Reloading configuration files</li>
 *   <li>Providing typed access to configurations</li>
 *   <li>Managing configuration lifecycles</li>
 * </ul>
 *
 * @author while1cry
 */
public class ConfigManager {

    private final UltimateHub plugin;
    private final Logger logger;

    /**
     * Main plugin configuration handler
     */
    @Getter
    private PluginConfig pluginConfig;

    /**
     * Spawn locations configuration handler
     */
    @Getter
    private SpawnConfig spawnConfig;

    /**
     * Creates configuration manager instance
     * @param plugin Main plugin instance for file access
     */
    public ConfigManager(UltimateHub plugin) {
        this.plugin = plugin;
        this.logger = plugin.getSLF4JLogger();
    }

    /**
     * Loads all configuration files
     * <p>Initializes:
     * <ol>
     *   <li>Plugin main config (config.yml)</li>
     *   <li>Spawn locations config (spawns.yml)</li>
     * </ol>
     */
    public void load() {
        logger.info("Loading config...");
        pluginConfig = new PluginConfigImpl(plugin);
        spawnConfig = new SpawnConfigImpl(plugin);
    }

}