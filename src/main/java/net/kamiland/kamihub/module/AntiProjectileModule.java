package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiProjectileModule extends EventModule {

    private final ConfigManager configManager;
    private ModuleConfig config;
    private MessageConfig messages;

    public AntiProjectileModule(KamiHub plugin) {
        super(plugin, "anti-projectile");
        this.configManager = plugin.getConfigManager();
    }

    @Override
    protected void load() {
        config = configManager.getModuleConfig();
        messages = configManager.getMessageConfig();
    }

    @Override
    protected void unload() {
        config = null;
        messages = null;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (isEnabled() && config.ANTIPROJECTILE_WORLDS.contains(event.getEntity().getWorld().getName())) {
            if (config.IS_ANTIPROJECTILE_PLAYER && event.getEntity().getShooter() instanceof Player shooter && ! shooter.hasPermission(getBypassPermission())) {
                event.setCancelled(true);
                shooter.sendMessage(messages.getMessage(shooter, "modules.anti-projectile"));
            }
            else if (config.IS_ANTIPROJECTILE_ENTITY)
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
        return "kamihub.anti-projectile.bypass";
    }

}
