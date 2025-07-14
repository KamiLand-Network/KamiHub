package net.kamiland.ultimatehub.command;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kamiland.ultimatehub.module.AgreementModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

@Command(name = "agreement")
public class AgreementCommand {

    private final AgreementModule agreementModule;

    public AgreementCommand(AgreementModule agreementModule) {
        this.agreementModule = agreementModule;
    }

    @Execute(name = "agree")
    void agree(@Context Player player) {
        Map<UUID, Integer> playerTaskMap = agreementModule.playerTaskMap;
        Bukkit.getScheduler().cancelTask(playerTaskMap.get(player.getUniqueId()));
        playerTaskMap.remove(player.getUniqueId());
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
