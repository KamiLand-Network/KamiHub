package net.kamiland.kamihub.manager;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.handler.result.ResultHandlerChain;
import dev.rollczi.litecommands.invalidusage.InvalidUsage;
import dev.rollczi.litecommands.invalidusage.InvalidUsageHandler;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.permission.MissingPermissions;
import dev.rollczi.litecommands.permission.MissingPermissionsHandler;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.command.AgreementCommand;
import net.kamiland.kamihub.command.SpawnCommand;
import net.kamiland.kamihub.command.KamiHubCommand;
import net.kamiland.kamihub.config.MessageConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager {

    private final KamiHub plugin;
    private final MessageConfig messages;
    private LiteCommands<CommandSender> liteCommands;


    public CommandManager(KamiHub plugin) {
        this.plugin = plugin;
        this.messages = plugin.getConfigManager().getMessageConfig();
    }

    public void registerCommands() {
        liteCommands = LiteBukkitFactory.builder(plugin)
                .commands(
                        new AgreementCommand(plugin),
                        new SpawnCommand(plugin),
                        new KamiHubCommand(plugin)
                )
                .missingPermission(new PermissionsHandler())
                .invalidUsage(new UsageHandler())
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
                player.sendMessage(messages.getMessage(player, "general.no-permission", permissions));
            else
                invocation.sender().sendMessage(messages.getMessage("general.no-permission", permissions));
        }

    }

    class UsageHandler implements InvalidUsageHandler<CommandSender> {

        @Override
        public void handle(Invocation<CommandSender> invocation, InvalidUsage<CommandSender> result, ResultHandlerChain<CommandSender> chain) {
            if (invocation.sender() instanceof Player player)
                player.sendMessage(messages.getMessage(player, "general.invalid-usage"));
            else
                invocation.sender().sendMessage(messages.getMessage("general.invalid-usage"));
        }

    }

}
