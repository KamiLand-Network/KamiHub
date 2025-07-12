package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ActionBarModule extends Module {

    private final UltimateHub plugin;
    private final ConfigManager configManager;
    private BukkitTask actionBarTimerTask;

    public ActionBarModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "action-bar");
        this.plugin = plugin;
        this.configManager = configManager;

        setEnabled(configManager.getModuleConfig().IS_ACTIONBAR_ENABLED);
    }

    @Override
    protected void load() {
        if (actionBarTimerTask != null) {
            actionBarTimerTask.cancel();
        }
        actionBarTimerTask = new BukkitRunnable() {
            final Map<World, Integer> worldsMap = new HashMap<>();
            @Override
            public void run() {

            }
        }.runTaskTimer(plugin, 0L, configManager.getModuleConfig().ACTIONBAR_INTERVAL);
    }

    @Override
    protected void unload() {
        if (actionBarTimerTask != null) {
            actionBarTimerTask.cancel();
            actionBarTimerTask = null;
        }
    }

    @Override
    @NotNull
    public String getPermission() {
        return "ultimatehub.action-bar";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
