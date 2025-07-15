package net.kamiland.ultimatehub;

import net.kamiland.ultimatehub.data.impl.cp.HikariManager;
import net.kamiland.ultimatehub.data.impl.player.RuntimePlayerDataManager;
import net.kamiland.ultimatehub.data.manager.cp.ConnectionPoolManager;
import net.kamiland.ultimatehub.manager.CommandManager;
import net.kamiland.ultimatehub.manager.ConfigManager;
import net.kamiland.ultimatehub.manager.ModuleManager;
import net.kamiland.ultimatehub.manager.ServerManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

public class UltimateHub extends JavaPlugin {

    private Logger logger;
    private ServerManager serverManager;
    private ConfigManager configManager;
    private ConnectionPoolManager cpManager;
    private RuntimePlayerDataManager runtimePDM;
    private ModuleManager moduleManager;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        logger = getSLF4JLogger();

        serverManager = new ServerManager(this);
        serverManager.load();

        configManager = new ConfigManager(this);
        configManager.load();

        cpManager = new HikariManager(this, configManager);
        cpManager.init();
        runtimePDM = new RuntimePlayerDataManager(this, configManager, cpManager);

        moduleManager = new ModuleManager(this, configManager);

        commandManager = new CommandManager(this, moduleManager, configManager);
        commandManager.registerCommands();
    }

    @Override
    public void onDisable() {
        if (commandManager != null) commandManager.unRegisterCommands();
        if (moduleManager != null) moduleManager.disableAll();
        if (cpManager != null) cpManager.close();
    }

}
