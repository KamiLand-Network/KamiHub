package net.kamiland.kamihub.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.flag.Flag;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.manager.ModuleManager;
import net.kamiland.kamihub.module.Module;
import net.kamiland.kamihub.util.GitHubRelease;
import net.kamiland.kamihub.util.GitHubUpdateChecker;
import net.kamiland.kamihub.util.MessageUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

@Command(name = "kamihub", aliases = {"khub", "kh"})
public class KamiHubCommand {

    private final KamiHub plugin;
    private final MessageConfig messages;
    private final ModuleManager moduleManager;

    public KamiHubCommand(KamiHub plugin) {
        this.plugin = plugin;
        this.messages = plugin.getConfigManager().getMessageConfig();
        this.moduleManager = plugin.getModuleManager();
    }

    @Execute
    void executeKamiHub(@Context CommandSender sender) {
        sender.sendMessage(MessageUtil.getMessage(
                "<white>------------ <aqua>KamiHub v" + KamiHub.CURRENT_VERSION + " <white>------------<newline>" +
                        "<click:open_url:'https://www.kamiland.net/plugins/kamihub'><green>[Website]</click>    <click:open_url:'https://github.com/KamiLand-Network/KamiHub'><aqua>[GitHub]</click><newline>" +
                        "<green>Run <yellow>/kh help <green> for help<newline>" +
                        "<gray>Powered by KamiLand"
        ));
    }

    @Execute(name = "info")
    void executeInfo(@Context CommandSender sender) {
        executeKamiHub(sender);
    }

    @Execute(name = "help")
    @Permission("kamihub.help")
    void executeHelp(@Context CommandSender sender) {
        executeHelp(sender, 1);
    }

    @Execute(name = "help")
    @Permission("kamihub.help")
    void executeHelp(@Context CommandSender sender, @Arg int page) {
        if (sender instanceof Player pSender) {
            Component helpMsg = messages.getCommandHelpMessage(pSender, page);
            helpMsg = helpMsg != null ? helpMsg : messages.getMessage(pSender, "general.unknown-command-help");
            pSender.sendMessage(helpMsg);
        } else {
            sender.sendMessage(messages.getMessage("modules.kamihub.help"));
        }
    }

    @Execute(name = "module list")
    @Permission("kamihub.module.list")
    void executeModuleList(@Context CommandSender sender) {
        sender.sendMessage(messages.getMessageWithComponentReplacements((sender instanceof Player ? (Player) sender : null), "modules.list", moduleManager.getModulesComponent()));
    }

    @Execute(name = "module enable")
    @Permission("kamihub.module.enable")
    void executeModuleEnable(@Context CommandSender sender, @Arg Module module) {
        if (module == null) {
            sender.sendMessage(messages.getMessage((sender instanceof Player ? (Player) sender : null), "modules.not-found"));
            return;
        }
        moduleManager.enable(module.getName());
        sender.sendMessage(messages.getMessageWithComponentReplacements((sender instanceof Player ? (Player) sender : null), "modules.enabled", module.toComponent()));
    }

    @Execute(name = "module disable")
    @Permission("kamihub.module.disable")
    void executeModuleDisable(@Context CommandSender sender, @Arg Module module) {
        if (module == null) {
            sender.sendMessage(messages.getMessage((sender instanceof Player ? (Player) sender : null), "modules.not-found"));
            return;
        }
        moduleManager.disable(module.getName());
        sender.sendMessage(messages.getMessageWithComponentReplacements((sender instanceof Player ? (Player) sender : null), "modules.disabled", module.toComponent()));
    }

    @Execute(name = "module toggle")
    @Permission("kamihub.module.toggle")
    void executeModuleToggle(@Context CommandSender sender, @Arg Module module, @Flag("-r") boolean resend) {
        if (module == null) {
            sender.sendMessage(messages.getMessage((sender instanceof Player ? (Player) sender : null), "modules.not-found"));
            return;
        }
        boolean before = module.isEnabled();
        if (before)
            moduleManager.disable(module.getName());
        else
            moduleManager.enable(module.getName());

        if (resend)
            sender.sendMessage(messages.getMessageWithComponentReplacements((sender instanceof Player ? (Player) sender : null), "modules.list", moduleManager.getModulesComponent()));
        else
            sender.sendMessage(messages.getMessageWithComponentReplacements((sender instanceof Player ? (Player) sender : null), before ? "modules.disabled" : "modules.enabled", module.toComponent()));
    }

    @Execute(name = "reload")
    @Permission("kamihub.reload")
    void executeReload(@Context CommandSender sender) {
        plugin.onReload(sender);
    }

    @Execute(name = "update")
    @Permission("kamihub.update")
    void executeUpdate(@Context CommandSender sender) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            sender.sendMessage(MessageUtil.getMessage("<green>Checking for updates..."));
            Optional<GitHubRelease> newVersion = new GitHubUpdateChecker("https://api.github.com/repos/KamiLand-Network/KamiHub/releases",
                    KamiHub.CURRENT_VERSION,
                    release -> !release.prerelease())
                    .fetchLatestRelease(plugin.getSLF4JLogger());
            newVersion.ifPresentOrElse(gitHubRelease -> sender.sendMessage(MessageUtil.getMessage(
                    "<green>New version available: <yellow>{0}<green>, Download the latest version at {1}",
                    gitHubRelease.body(),
                    "https://github.com/KamiLand-Network/KamiHub/releases"
            )), () -> {
                sender.sendMessage(MessageUtil.getMessage("<green>No new version available"));
            });
        });
    }

}
