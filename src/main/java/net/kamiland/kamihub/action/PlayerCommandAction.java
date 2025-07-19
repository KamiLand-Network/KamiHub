package net.kamiland.kamihub.action;

import net.kamiland.kamihub.KamiHub;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class PlayerCommandAction extends Action {

    private final KamiHub plugin;

    protected PlayerCommandAction(KamiHub plugin) {
        super("[player]");
        this.plugin = plugin;
    }

    @Override
    public void resolve(@Nullable Player player, String value) {
        if (player == null) {
            plugin.getSLF4JLogger().warn("This action is not triggered by the player! {}", value);
            return;
        }
        player.performCommand(value.replaceAll("\\{0}", player.getName()));
    }

}
