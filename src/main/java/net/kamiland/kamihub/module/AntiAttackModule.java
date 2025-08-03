package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiAttackModule extends EventModule {

    private final ConfigManager configManager;
    private ModuleConfig config;
    private MessageConfig messages;

    public AntiAttackModule(KamiHub plugin) {
        super(plugin, "anti-attack");
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
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (isEnabled() && config.ANTIATTACK_WORLDS.contains(event.getEntity().getWorld().getName())) {
            if (event.getDamager().hasPermission(getBypassPermission()))
                return;
            if (event.getDamager() instanceof Player damager) {
                if (config.IS_ANTIATTACK_ENTITY && ! (event.getEntity() instanceof Player)) {
                    event.setCancelled(true);
                    damager.sendMessage(messages.getMessage(damager, "modules.anti-attack"));
                }
                if (config.IS_ANTIATTACK_PLAYER && event.getEntity() instanceof Player) {
                    event.setCancelled(true);
                    damager.sendMessage(messages.getMessage(damager, "modules.anti-attack"));
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
        return "kamihub.anti-attack";
    }

}
