package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiInteractModule extends EventModule {

    private final ConfigManager configManager;

    public AntiInteractModule(KamiHub plugin) {
        super(plugin, "anti-interact");
        this.configManager = plugin.getConfigManager();

        setEnabled(configManager.getModuleConfig().IS_ANTIINTERACT_ENABLED);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (isEnabled() && configManager.getModuleConfig().ANTIINTERACT_WORLDS.contains(event.getPlayer().getWorld().getName())) {
            if (event.getPlayer().hasPermission(getBypassPermission())) {
                if (configManager.getModuleConfig().IS_ANTIINTERACT_CREATIVE_ONLY) {
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
        return "kamihub.anti-interact.bypass";
    }

}
