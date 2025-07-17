package net.kamiland.kamihub;

import lombok.Getter;
import net.kamiland.kamihub.data.impl.cp.HikariManager;
import net.kamiland.kamihub.data.impl.player.RuntimePlayerDataManager;
import net.kamiland.kamihub.data.manager.cp.ConnectionPoolManager;
import net.kamiland.kamihub.manager.CommandManager;
import net.kamiland.kamihub.manager.ConfigManager;
import net.kamiland.kamihub.manager.ModuleManager;
import net.kamiland.kamihub.manager.ServerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class KamiHub extends JavaPlugin {

    private Logger logger;
    @Getter
    private ServerManager serverManager;
    @Getter
    private ConfigManager configManager;
    @Getter
    private ConnectionPoolManager connectionPoolManager;
    @Getter
    private RuntimePlayerDataManager runtimePDM;
    @Getter
    private ModuleManager moduleManager;
    @Getter
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        logger = getSLF4JLogger();

        serverManager = new ServerManager(this);
        serverManager.load();

        configManager = new ConfigManager(this);
        configManager.load();

        connectionPoolManager = new HikariManager(this);
        connectionPoolManager.init();
        runtimePDM = new RuntimePlayerDataManager(this);

        moduleManager = new ModuleManager(this);
        moduleManager.load();

        commandManager = new CommandManager(this);
        commandManager.registerCommands();
    }

    public void onReload(@Nullable CommandSender feedback) {
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            if (commandManager != null) commandManager.unRegisterCommands();
            if (moduleManager != null) moduleManager.disableAll();
            if (connectionPoolManager != null) connectionPoolManager.close();

            serverManager.load();
            configManager.load();

            connectionPoolManager = new HikariManager(this);
            connectionPoolManager.init();
            runtimePDM = new RuntimePlayerDataManager(this);

            moduleManager = new ModuleManager(this);

            commandManager = new CommandManager(this);
            commandManager.registerCommands();

            if (feedback == null) return;
            Bukkit.getScheduler().runTask(this, () -> {
                if (feedback instanceof Player pFeedback)
                    feedback.sendMessage(configManager.getMessageConfig().getMessage(pFeedback, "general.reload-success"));
                else
                    feedback.sendMessage(configManager.getMessageConfig().getMessage("general.reload-success"));
            });
        });
    }

    @Override
    public void onDisable() {
        if (commandManager != null) commandManager.unRegisterCommands();
        if (moduleManager != null) moduleManager.disableAll();
        if (connectionPoolManager != null) connectionPoolManager.close();
    }

}
