package net.kamiland.kamihub.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "kamihub", aliases = {"khub", "kh"})
public class KamiHubCommand {

    private final KamiHub plugin;
    private final MessageConfig messages;

    public KamiHubCommand(KamiHub plugin) {
        this.plugin = plugin;
        this.messages = plugin.getConfigManager().getMessageConfig();
    }

    @Execute
    void executeKamiHub(@Context CommandSender sender) {
        executeHelp(sender, 1);
    }

    @Execute(name = "help")
    void executeHelp(@Context CommandSender sender) {
        executeHelp(sender, 1);
    }

    @Execute(name = "help")
    void executeHelp(@Context CommandSender sender, @Arg int page) {
        if (sender instanceof Player pSender) {
            Component helpMsg = messages.getCommandHelpMessage(pSender, page);
            helpMsg = helpMsg != null ? helpMsg : messages.getMessage(pSender, "general.unknown-command-help");
            pSender.sendMessage(helpMsg);
        } else {
            sender.sendMessage(messages.getMessage("modules.kamihub.help"));
        }
    }

    @Execute(name = "reload")
    @Permission("kamihub.reload")
    void executeReload(@Context CommandSender sender) {
        plugin.onReload(sender);
    }

}
