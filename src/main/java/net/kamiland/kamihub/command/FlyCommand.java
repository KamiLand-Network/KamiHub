package net.kamiland.kamihub.command;import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.module.FlyModule;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

@Command(name = "fly")
@Permission("kamihub.fly")
public class FlyCommand {

    private final MessageConfig messages;
    private final FlyModule flyModule;

    public FlyCommand(KamiHub plugin) {
        this.messages = plugin.getConfigManager().getMessageConfig();
        this.flyModule = (FlyModule) plugin.getModuleManager().getModule("fly");
    }

    @Execute
    void executeFly(@Context Player player) {
        flyModule.setAllowFly(player, !flyModule.isAllowFly(player));
        if (flyModule.isAllowFly(player)) {
            player.setVelocity(player.getVelocity().add(new Vector(0, 0.5, 0)));
            player.sendMessage(messages.getMessage("modules.fly.enable"));
        } else {
            player.sendMessage(messages.getMessage("modules.fly.disable"));
        }
    }

}
