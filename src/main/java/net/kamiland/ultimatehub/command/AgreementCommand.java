package net.kamiland.ultimatehub.command;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kamiland.ultimatehub.module.AgreementModule;
import org.bukkit.entity.Player;

@Command(name = "agreement")
public class AgreementCommand {

    private final AgreementModule agreementModule;

    public AgreementCommand(AgreementModule agreementModule) {
        this.agreementModule = agreementModule;
    }

    @Execute(name = "agree")
    void agree(@Context Player player) {
        agreementModule.playerTaskMap.remove(player.getUniqueId());
    }

    @Execute(name = "reject")
    void reject(@Context Player player) {
        agreementModule.kickPlayerOnDisagree(player);
    }

    @Execute(name = "change")
    @Permission("ultimatehub.agreement.change")
    void change() {
        // TODO
    }
}
