package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiPickupModule extends EventModule {

    private final UltimateHub plugin;
    private final ConfigManager configManager;

    public AntiPickupModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "anti-drop");
        this.plugin = plugin;
        this.configManager = configManager;

        setEnabled(configManager.getModuleConfig().IS_ANTIPICKUP_ENABLED);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onPlayerAttemptPickupItem(PlayerAttemptPickupItemEvent event) {
        if (isEnabled() && configManager.getModuleConfig().ANTIPICKUP_WORLDS.contains(event.getPlayer().getWorld().getName()) && ! event.getPlayer().hasPermission(getBypassPermission())) {
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
        return "ultimatehub.anti-pickup.bypass";
    }

}
