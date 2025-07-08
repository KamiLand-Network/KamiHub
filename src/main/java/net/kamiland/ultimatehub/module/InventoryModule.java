package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.Nullable;

public class InventoryModule implements Module, Listener {

    private final UltimateHub plugin;
    private final ConfigManager configManager;
    private boolean enabled = false;

    public InventoryModule(UltimateHub plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public String getName() {
        return "inventory";
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
        // TODO: Implement
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!enabled)
            return;
        Player player = event.getPlayer();
        if (configManager.getPluginConfig().IS_INVENTORY_CLEAR_ON_JOIN && (getBypassPermission() == null || !player.hasPermission(getBypassPermission()))) {
            player.getInventory().clear();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!enabled)
            return;
        Player player = event.getPlayer();
        if (configManager.getPluginConfig().IS_INVENTORY_CLEAR_ON_QUIT && (getBypassPermission() == null ||!player.hasPermission(getBypassPermission()))) {
            player.getInventory().clear();
        }
    }

    @Override
    @Nullable
    public String getPermission() {
        return "ultimatehub.inventory";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return "ultimatehub.inventory.bypass";
    }

}
