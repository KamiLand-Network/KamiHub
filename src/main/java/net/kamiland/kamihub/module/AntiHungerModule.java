package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiHungerModule extends EventModule {

    private final ConfigManager configManager;
    private ModuleConfig config;

    public AntiHungerModule(KamiHub plugin) {
        super(plugin, "anti-hunger");
        this.configManager = plugin.getConfigManager();
    }

    @Override
    protected void load() {
        config = configManager.getModuleConfig();
    }

    @Override
    protected void unload() {
        config = null;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (isEnabled() && config.ANTIHUNGER_WORLDS.contains(event.getEntity().getWorld().getName()) && event.getEntity() instanceof Player && event.getEntity().hasPermission(getPermission())) {
            event.setCancelled(true);
            event.getEntity().setFoodLevel(20);
        }
    }

    @Override
    @NotNull
    public String getPermission() {
        return "kamihub.anti-hunger";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
