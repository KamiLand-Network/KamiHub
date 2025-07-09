package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiBreakModule extends EventModule {

    private final UltimateHub plugin;
    private final ConfigManager configManager;

    public AntiBreakModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "anti-break");
        this.plugin = plugin;
        this.configManager = configManager;

        setEnabled(configManager.getPluginConfig().IS_ANTIBREAK_ENABLED);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (isEnabled()) {
            if (event.getPlayer().hasPermission(getBypassPermission())) {
                if (configManager.getPluginConfig().IS_ANTIBREAK_CREATIVE_ONLY) {
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
        return "ultimatehub.antibreak.bypass";
    }

}
