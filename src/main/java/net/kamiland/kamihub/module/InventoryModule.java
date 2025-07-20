package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ModuleConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InventoryModule extends EventModule {

    private final ModuleConfig config;

    public InventoryModule(KamiHub plugin) {
        super(plugin, "inventory");
        this.config = plugin.getConfigManager().getModuleConfig();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (isEnabled() && config.IS_INVENTORY_CLEAR_ON_JOIN && ! event.getPlayer().hasPermission(getBypassPermission())) {
            event.getPlayer().getInventory().clear();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (isEnabled() && config.IS_INVENTORY_CLEAR_ON_QUIT && ! event.getPlayer().hasPermission(getBypassPermission())) {
            event.getPlayer().getInventory().clear();
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (isEnabled() && config.IS_INVENTORY_PREVENT_MOVING) {
            Player player = (Player) event.getView().getPlayer();
            if (player.hasPermission(getBypassPermission()))
                return;
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
        return "kamihub.inventory.bypass";
    }

}