package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiDamageModule extends EventModule {

    private final UltimateHub plugin;
    private final ConfigManager configManager;

    public AntiDamageModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "anti-damage");
        this.plugin = plugin;
        this.configManager = configManager;

        setEnabled(configManager.getPluginConfig().IS_ANTIDAMAGE_ENABLED);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (isEnabled()) {
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
