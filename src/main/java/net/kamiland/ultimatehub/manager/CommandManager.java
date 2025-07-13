package net.kamiland.ultimatehub.manager;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.command.AgreementCommand;
import net.kamiland.ultimatehub.command.SpawnCommand;
import net.kamiland.ultimatehub.command.UltimateHubCommand;
import org.bukkit.command.CommandSender;

public class CommandManager {

    private final UltimateHub plugin;
    private LiteCommands<CommandSender> liteCommands;


    public CommandManager(UltimateHub plugin) {
        this.plugin = plugin;
    }

    public void registerCommands() {
        this.liteCommands = LiteBukkitFactory.builder(plugin)
                .commands(
                        new AgreementCommand(),
                        new SpawnCommand(),
                        new UltimateHubCommand()
                )
                .build();
    }

    public void unRegisterCommands() {
        if (this.liteCommands != null) this.liteCommands.unregister();
    }
}
