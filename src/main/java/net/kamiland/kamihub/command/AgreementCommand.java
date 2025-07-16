package net.kamiland.kamihub.command;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.module.AgreementModule;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(name = "agreement")
public class AgreementCommand {

    private final AgreementModule agreementModule;
    private final MessageConfig messages;

    public AgreementCommand(KamiHub plugin) {
        this.agreementModule = (AgreementModule) plugin.getModuleManager().getModule("agreement");
        this.messages = plugin.getConfigManager().getMessageConfig();
    }

    @Execute(name = "accept")
    void accept(@Context Player player) {
        agreementModule.onAccept(player);
        player.sendMessage(messages.getMessage(player, "modules.agreement.accept"));
    }

    @Execute(name = "reject")
    void reject(@Context Player player) {
        agreementModule.onReject(player);
        player.sendMessage(messages.getMessage(player, "modules.agreement.reject"));
    }

    @Execute(name = "change")
    @Permission("kamihub.agreement.change")
    void change(@Context CommandSender sender) {
        agreementModule.onChange();
        Player pSender = (sender instanceof Player) ? (Player) sender : null;
        sender.sendMessage(messages.getMessage(pSender, "modules.agreement.change"));
    }

}
