package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiAttackModule extends EventModule {

    private final ConfigManager configManager;
    private final MessageConfig messages;

    public AntiAttackModule(KamiHub plugin) {
        super(plugin, "anti-attack");
        this.configManager = plugin.getConfigManager();
        this.messages = configManager.getMessageConfig();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (isEnabled() && configManager.getModuleConfig().ANTIATTACK_WORLDS.contains(event.getEntity().getWorld().getName())) {
            if (event.getDamager().hasPermission(getBypassPermission()))
                return;
            if (event.getDamager() instanceof Player damager) {
                if (configManager.getModuleConfig().IS_ANTIATTACK_ENTITY && ! (event.getEntity() instanceof Player)) {
                    event.setCancelled(true);
                    damager.sendMessage(messages.getMessage(damager, "modules.anti-attack"));
                }
                if (configManager.getModuleConfig().IS_ANTIATTACK_PLAYER && event.getEntity() instanceof Player) {
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
