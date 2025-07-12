package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiProjectileModule extends EventModule {

    private final UltimateHub plugin;
    private final ConfigManager configManager;

    public AntiProjectileModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "anti-projectile");
        this.plugin = plugin;
        this.configManager = configManager;

        setEnabled(configManager.getModuleConfig().IS_ANTIPROJECTILE_ENABLED);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (isEnabled() && configManager.getModuleConfig().ANTIPROJECTILE_WORLDS.contains(event.getEntity().getWorld().getName())) {
            if (configManager.getModuleConfig().IS_ANTIPROJECTILE_PLAYER && event.getEntity().getShooter() instanceof Player shooter && ! shooter.hasPermission(getBypassPermission()))
                event.setCancelled(true);
            else if (configManager.getModuleConfig().IS_ANTIPROJECTILE_ENTITY)
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
        return "ultimatehub.anti-projectile.bypass";
    }

}
