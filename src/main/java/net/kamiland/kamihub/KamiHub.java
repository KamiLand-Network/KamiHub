package net.kamiland.kamihub;

import lombok.Getter;
import net.kamiland.kamihub.action.ActionResolver;
import net.kamiland.kamihub.data.impl.cp.HikariManager;
import net.kamiland.kamihub.data.impl.player.RuntimePlayerDataManager;
import net.kamiland.kamihub.data.manager.cp.ConnectionPoolManager;
import net.kamiland.kamihub.manager.CommandManager;
import net.kamiland.kamihub.manager.ConfigManager;
import net.kamiland.kamihub.manager.ModuleManager;
import net.kamiland.kamihub.manager.ServerManager;
import net.kamiland.kamihub.util.GitHubRelease;
import net.kamiland.kamihub.util.GitHubUpdateChecker;
import net.kamiland.kamihub.util.MessageUtil;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Optional;

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
    private ActionResolver actionResolver;
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

        actionResolver = new ActionResolver(this);

        moduleManager = new ModuleManager(this);
        moduleManager.load();

        commandManager = new CommandManager(this);
        commandManager.registerCommands();

        new Metrics(this, 26537);

        if (configManager.getPluginConfig().CHECK_FOR_UPDATES) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(this, () -> {
                logger.info("Checking for updates...");
                Optional<GitHubRelease> newVersion = new GitHubUpdateChecker("https://api.github.com/repos/KamiLand-Network/KamiHub/releases",
                        "1.0-alpha",
                        release -> !release.prerelease())
                        .fetchLatestRelease(logger);
                newVersion.ifPresentOrElse(gitHubRelease -> getComponentLogger().info(MessageUtil.getMessage(
                        "<green>New version available: <yellow>{0}<green>, Download the latest version at {1}",
                        gitHubRelease.body(),
                        "https://github.com/KamiLand-Network/KamiHub/releases"
                )), () -> {
                    logger.info("No new version available");
                });
            }, 100L);
        }
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
            moduleManager.load();

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
