package net.kamiland.kamihub.action;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.util.MessageUtil;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class BroadcastAction extends Action{

    private final KamiHub plugin;

    public BroadcastAction(KamiHub plugin) {
        super("[broadcast]");
        this.plugin = plugin;
    }

    @Override
    public void resolve(@Nullable Player player, String value) {
        if (player == null && value.contains("\\{0}")) {
            plugin.getSLF4JLogger().warn("This action is not triggered by the player! {}", value);
            return;
        }
        assert player != null;
        plugin.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(MessageUtil.getMessage(p, value, player.getName())));
    }

}
