package net.kamiland.kamihub.manager;

import lombok.Getter;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.*;
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

    private final KamiHub plugin;
    private final Logger logger;

    /**
     * Main plugin configuration handler
     */
    @Getter
    private PluginConfig pluginConfig;

    /**
     * Module configuration handler
     */
    @Getter
    private ModuleConfig moduleConfig;

    /**
     * Spawn locations configuration handler
     */
    @Getter
    private SpawnConfig spawnConfig;

    /**
     * Messages configuration handler
     */
    @Getter
    private MessageConfig messageConfig;

    /**
     * Item configuration handler
     */
    @Getter
    private ItemConfig itemConfig;

    /**
     * Creates configuration manager instance
     * @param plugin Main plugin instance for file access
     */
    public ConfigManager(KamiHub plugin) {
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
        pluginConfig = new PluginConfig(plugin);
        pluginConfig.load();
        itemConfig = new ItemConfig(plugin);
        itemConfig.load();
        moduleConfig = new ModuleConfig(plugin);
        moduleConfig.load();
        spawnConfig = new SpawnConfig(plugin);
        spawnConfig.load();
        messageConfig = new MessageConfig(plugin);
        messageConfig.load();

        pluginConfig.updateConfigComplete();
    }

}