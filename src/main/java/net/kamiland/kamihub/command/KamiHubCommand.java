package net.kamiland.kamihub.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import org.bukkit.command.CommandSender;

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

    }

}
