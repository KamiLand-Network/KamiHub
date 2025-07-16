package net.kamiland.kamihub;

import net.kamiland.kamihub.data.impl.cp.HikariManager;
import net.kamiland.kamihub.data.impl.player.RuntimePlayerDataManager;
import net.kamiland.kamihub.data.manager.cp.ConnectionPoolManager;
import net.kamiland.kamihub.manager.CommandManager;
import net.kamiland.kamihub.manager.ConfigManager;
import net.kamiland.kamihub.manager.ModuleManager;
import net.kamiland.kamihub.manager.ServerManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

public class KamiHub extends JavaPlugin {

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

        moduleManager = new ModuleManager(this, configManager, runtimePDM);

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
