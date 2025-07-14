package net.kamiland.ultimatehub.manager;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.command.AgreementCommand;
import net.kamiland.ultimatehub.command.SpawnCommand;
import net.kamiland.ultimatehub.command.UltimateHubCommand;
import net.kamiland.ultimatehub.module.AgreementModule;
import org.bukkit.command.CommandSender;

public class CommandManager {

    private final UltimateHub plugin;
    private final ModuleManager moduleManager;
    private LiteCommands<CommandSender> liteCommands;


    public CommandManager(UltimateHub plugin, ModuleManager moduleManager) {
        this.plugin = plugin;
        this.moduleManager = moduleManager;
    }

    public void registerCommands() {
        liteCommands = LiteBukkitFactory.builder(plugin)
                .commands(
                        new AgreementCommand((AgreementModule) moduleManager.getModule("agreement")),
                        new SpawnCommand(),
                        new UltimateHubCommand()
                )
                .build();
    }

    public void unRegisterCommands() {
        if (liteCommands != null) liteCommands.unregister();
    }
}
