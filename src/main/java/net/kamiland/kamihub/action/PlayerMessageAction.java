package net.kamiland.kamihub.action;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.util.MessageUtil;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class PlayerMessageAction extends Action {

    private final KamiHub plugin;

    protected PlayerMessageAction(KamiHub plugin) {
        super("[message]");
        this.plugin = plugin;
    }

    @Override
    public void resolve(@Nullable Player player, String value) {
        if (player == null) {
            plugin.getSLF4JLogger().warn("This action is not triggered by the player! {}", value);
            return;
        }
        player.sendMessage(MessageUtil.getMessage(player, value, player.getName()));
    }

}
