package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiDamageModule extends EventModule {

    private final KamiHub plugin;
    private final ConfigManager configManager;

    public AntiDamageModule(KamiHub plugin, ConfigManager configManager) {
        super(plugin, "anti-damage");
        this.plugin = plugin;
        this.configManager = configManager;

        setEnabled(configManager.getModuleConfig().IS_ANTIDAMAGE_ENABLED);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (isEnabled() && configManager.getModuleConfig().ANTIDAMAGE_WORLDS.contains(event.getEntity().getWorld().getName())) {
            if (event.getEntity() instanceof Player player) {
                if (player.hasPermission(getPermission())) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @Override
    @NotNull
    public String getPermission() {
        return "ultimatehub.anti-damage";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
