package net.kamiland.kamihub.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.command.CommandSender;

@Command(name = "ultimatehub", aliases = {"uhub", "uh"})
public class UltimateHubCommand {

    @Execute
    public void executeUltimateHub(@Context CommandSender sender) {
        help(sender);
    }

    @Execute(name = "help")
    public void help(@Context CommandSender sender) {

    }

    @Execute(name = "help")
    public void help(@Context CommandSender sender, @Arg int page) {

    }

}
