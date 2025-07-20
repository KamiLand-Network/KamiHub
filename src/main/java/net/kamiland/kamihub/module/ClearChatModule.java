package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClearChatModule extends EventModule {

    public ClearChatModule(KamiHub plugin) {
        super(plugin, "clear-chat");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (isEnabled() && ! event.getPlayer().hasPermission(getBypassPermission())) {
            for (int i = 0; i < 100; i ++)
                event.getPlayer().sendMessage("");
        }
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @Override
    @Nullable
    public String getPermission() {
        return null;
    }

    @Override
    @NotNull
    public String getBypassPermission() {
        return "kamihub.clear-chat.bypass";
    }

}
