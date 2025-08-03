package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ItemConfig;
import net.kamiland.kamihub.config.ModuleConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InventoryModule extends EventModule {

    private ModuleConfig config;
    private ItemConfig itemConfig;

    public InventoryModule(KamiHub plugin) {
        super(plugin, "inventory");
    }

    @Override
    protected void load() {
        config = plugin.getConfigManager().getModuleConfig();
        itemConfig = plugin.getConfigManager().getItemConfig();
    }

    @Override
    protected void unload() {
        config = null;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (isEnabled() && ! event.getPlayer().hasPermission(getBypassPermission())) {
            if (config.IS_INVENTORY_CLEAR_ON_JOIN)
                event.getPlayer().getInventory().clear();
            if (config.IS_INVENTORY_GIVE_ON_JOIN)
                itemConfig.getItemMap().forEach((slot, itemStack) -> event.getPlayer().getInventory().setItem(slot, itemStack));
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
        if (isEnabled()) {
            Player player = (Player) event.getView().getPlayer();
            if (config.IS_INVENTORY_PREVENT_MOVING) {
                if (player.hasPermission(getBypassPermission()))
                    return;
                event.setCancelled(true);
            }
            if (event.getCurrentItem() != null && itemConfig.getItemMap().containsValue(event.getCurrentItem())) {
                event.setCancelled(true);
                String[] actions = itemConfig.getActions(event.getCurrentItem());
                for (String action : actions) {
                    plugin.getActionResolver().resolve(player, action);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (isEnabled()) {
            Player player = event.getPlayer();
            if (event.getItem() != null && itemConfig.getItemMap().containsValue(event.getItem())) {
                event.setCancelled(true);
                String[] actions = itemConfig.getActions(event.getItem());
                for (String action : actions) {
                    plugin.getActionResolver().resolve(player, action);
                }
            }
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