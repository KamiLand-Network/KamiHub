package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiHungerModule extends EventModule {

    private final UltimateHub plugin;
    private final ConfigManager configManager;

    public AntiHungerModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "");
        this.plugin = plugin;
        this.configManager = configManager;

        setEnabled(configManager.getPluginConfig().IS_ANTIHUNGER_ENABLED);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (isEnabled() && event.getEntity() instanceof Player && ! event.getEntity().hasPermission(getPermission())) {
            event.setCancelled(true);
            event.getEntity().setFoodLevel(20);
        }
    }

    @Override
    @NotNull
    public String getPermission() {
        return "ultimatehub.anti-hunger";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
