package net.kamiland.kamihub.action;

import net.kamiland.kamihub.KamiHub;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class ConsoleCommandAction extends Action{

    private final KamiHub plugin;

    protected ConsoleCommandAction(KamiHub plugin) {
        super("[console]", "\\[console]");
        this.plugin = plugin;
    }

    @Override
    public void resolve(@Nullable Player player, String value) {
        if (player == null && value.contains("{0}")) {
            plugin.getSLF4JLogger().warn("This action is not triggered by the player! {}", value);
            return;
        }
        assert player != null;
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), value.replaceAll("\\{0}", player.getName()));
    }

}
