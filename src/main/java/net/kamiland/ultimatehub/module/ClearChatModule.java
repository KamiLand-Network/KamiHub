package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.Nullable;

public class ClearChatModule implements Module, Listener {

    private final UltimateHub plugin;
    private final ConfigManager configManager;
    private boolean enabled = false;

    public ClearChatModule(UltimateHub plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        setEnabled(configManager.getPluginConfig().IS_CLEARCHAT_ENABLED);
    }

    @Override
    public String getName() {
        return "clearchat";
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled)
            return;
        this.enabled = enabled;
        setup();
    }

    @Override
    public void setup() {
        // Idk what to do with this...
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (enabled && getPermission() != null && event.getPlayer().hasPermission(getPermission())) {
            for (int i = 0; i < 100; i++)
                event.getPlayer().sendMessage("");
        }
    }

    @Override
    @Nullable
    public String getPermission() {
        return "ultimatehub.clearchat";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
