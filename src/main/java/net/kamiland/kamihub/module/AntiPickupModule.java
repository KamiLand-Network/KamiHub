package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiPickupModule extends EventModule {

    private final ConfigManager configManager;
    private ModuleConfig config;

    public AntiPickupModule(KamiHub plugin) {
        super(plugin, "anti-pickup");
        this.configManager = plugin.getConfigManager();
    }

    @Override
    protected void load() {
        config = configManager.getModuleConfig();
    }

    @Override
    protected void unload() {
        config = null;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerAttemptPickupItem(PlayerAttemptPickupItemEvent event) {
        if (isEnabled() && config.ANTIPICKUP_WORLDS.contains(event.getPlayer().getWorld().getName()) && ! event.getPlayer().hasPermission(getBypassPermission())) {
            event.setCancelled(true);
        }
    }

    @Override
    @Nullable
    public String getPermission() {
        return null;
    }

    @Override
    @NotNull
    public String getBypassPermission() {
        return "kamihub.anti-pickup.bypass";
    }

}
