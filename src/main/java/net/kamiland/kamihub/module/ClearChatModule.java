package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClearChatModule extends EventModule {

    private final KamiHub plugin;
    private final ConfigManager configManager;

    public ClearChatModule(KamiHub plugin, ConfigManager configManager) {
        super(plugin, "clear-chat");
        this.plugin = plugin;
        this.configManager = configManager;

        setEnabled(configManager.getModuleConfig().IS_CLEARCHAT_ENABLED);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (isEnabled() && event.getPlayer().hasPermission(getBypassPermission())) {
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
        return "ultimatehub.clear-chat.bypass";
    }

}
