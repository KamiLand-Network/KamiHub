package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiPlaceModule extends EventModule {

    private final ConfigManager configManager;

    public AntiPlaceModule(KamiHub plugin) {
        super(plugin, "anti-place");
        this.configManager = plugin.getConfigManager();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (isEnabled() && configManager.getModuleConfig().ANTIPLACE_WORLDS.contains(event.getPlayer().getWorld().getName())) {
            if (event.getPlayer().hasPermission(getBypassPermission())) {
                if (configManager.getModuleConfig().IS_ANTIPLACE_CREATIVE_ONLY) {
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
    @NotNull
    public String getBypassPermission() {
        return "kamihub.anti-place.bypass";
    }

}
