package net.kamiland.kamihub.command;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.module.AgreementModule;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.kamiland.kamihub.config.MessageConfig.Message.*;

@Command(name = "agreement")
public class AgreementCommand {

    private final AgreementModule agreementModule;
    private final MessageConfig message;

    public AgreementCommand(AgreementModule agreementModule, MessageConfig message) {
        this.agreementModule = agreementModule;
        this.message = message;
    }

    @Execute(name = "accept")
    void accept(@Context Player player) {
        agreementModule.onAccept(player);
        player.sendMessage(message.getMessage(player, AGREEMENT_ACCEPT));
    }

    @Execute(name = "reject")
    void reject(@Context Player player) {
        agreementModule.onReject(player);
        player.sendMessage(message.getMessage(player, AGREEMENT_REJECT));
    }

    @Execute(name = "change")
    @Permission("ultimatehub.agreement.change")
    void change(@Context CommandSender sender) {
        agreementModule.onChange();
        Player pSender = (sender instanceof Player) ? (Player) sender : null;
        sender.sendMessage(message.getMessage(pSender, AGREEMENT_CHANGE));
    }

}
