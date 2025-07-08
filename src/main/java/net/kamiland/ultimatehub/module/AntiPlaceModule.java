package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.Nullable;

public class AntiPlaceModule implements Module, Listener {

    private final UltimateHub plugin;
    private final ConfigManager configManager;
    private boolean enabled = false;

    public AntiPlaceModule(UltimateHub plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        setEnabled(configManager.getPluginConfig().IS_ANTIPLACE_ENABLED);
    }

    @Override
    public String getName() {
        return "antiinteract";
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

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (enabled) {
            if (getBypassPermission() != null && event.getPlayer().hasPermission(getBypassPermission())) {
                if (configManager.getPluginConfig().IS_ANTIPLACE_CREATIVE_ONLY) {
                    if (event.getPlayer().getGameMode() == GameMode.CREATIVE)
                        return;
                } else return;
            }
            event.setCancelled(true);
        }
    }

    @Override
    @Nullable
    public String getPermission() {
        return null;
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return "ultimatehub.antiplace.bypass";
    }

}
