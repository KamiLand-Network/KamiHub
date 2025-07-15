package net.kamiland.ultimatehub.manager;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.handler.result.ResultHandlerChain;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.permission.MissingPermissions;
import dev.rollczi.litecommands.permission.MissingPermissionsHandler;
import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.command.AgreementCommand;
import net.kamiland.ultimatehub.command.SpawnCommand;
import net.kamiland.ultimatehub.command.UltimateHubCommand;
import net.kamiland.ultimatehub.config.MessageConfig;
import net.kamiland.ultimatehub.module.AgreementModule;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.kamiland.ultimatehub.config.MessageConfig.Message.NO_PERMISSION;

public class CommandManager {

    private final UltimateHub plugin;
    private final ModuleManager moduleManager;
    private final MessageConfig message;
    private LiteCommands<CommandSender> liteCommands;


    public CommandManager(UltimateHub plugin, ModuleManager moduleManager, ConfigManager configManager) {
        this.plugin = plugin;
        this.moduleManager = moduleManager;
        this.message = configManager.getMessageConfig();
    }

    public void registerCommands() {
        liteCommands = LiteBukkitFactory.builder(plugin)
                .commands(
                        new AgreementCommand((AgreementModule) moduleManager.getModule("agreement"), message),
                        new SpawnCommand(),
                        new UltimateHubCommand()
                )
                .missingPermission(new PermissionsHandler())
                .build();
    }

    public void unRegisterCommands() {
        if (liteCommands != null) liteCommands.unregister();
    }

    class PermissionsHandler implements MissingPermissionsHandler<CommandSender> {

        @Override
        public void handle(Invocation<CommandSender> invocation, MissingPermissions missingPermissions, ResultHandlerChain<CommandSender> chain) {
            String permissions = missingPermissions.asJoinedText();
            if (invocation.sender() instanceof Player player)
                player.sendMessage(message.getMessage(player, NO_PERMISSION, permissions));
            else
                invocation.sender().sendMessage(message.getMessage(null, NO_PERMISSION, permissions));
        }

    }

}
