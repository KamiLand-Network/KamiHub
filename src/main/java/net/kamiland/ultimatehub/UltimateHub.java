package net.kamiland.ultimatehub;

import net.kamiland.ultimatehub.manager.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

public class UltimateHub extends JavaPlugin {

    private Logger logger;
    private ServerManager serverManager;
    private ConfigManager configManager;
    private PlayerManager playerManager;
    private ModuleManager moduleManager;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        logger = getSLF4JLogger();

        serverManager = new ServerManager(this);
        serverManager.load();

        configManager = new ConfigManager(this);
        configManager.load();

        playerManager = new PlayerManager(this);
        playerManager.loadAll();

        moduleManager = new ModuleManager(this, configManager);

        commandManager = new CommandManager(this);
        commandManager.registerCommands();
    }

    @Override
    public void onDisable() {
        moduleManager.disableAll();
        playerManager.unloadAll();
    }

}
