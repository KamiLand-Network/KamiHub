package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import net.kamiland.ultimatehub.util.MessageUtil;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;

public class ActionBarModule implements Module {

    private final UltimateHub plugin;
    private final ConfigManager configManager;
    private boolean enabled = false;
    private BukkitTask actionBarTimerTask;

    public ActionBarModule(UltimateHub plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
        setEnabled(configManager.getPluginConfig().IS_ACTIONBAR_ENABLED());
    }

    @Override
    public String getName() {
        return "actionbar";
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled)
            return;
        this.enabled = enabled;
        setup();
    }

    @Override
    public void setup() {
        if (enabled) {
            if (actionBarTimerTask != null) {
                actionBarTimerTask.cancel();
            }
            actionBarTimerTask = new BukkitRunnable() {
                int i = 0;
                @Override
                public void run() {
                    plugin.getServer().getOnlinePlayers().forEach(player -> {
                        if (getPermission() != null && player.hasPermission(getPermission()))
                            player.sendActionBar(MessageUtil.getMessage(player, configManager.getPluginConfig().ACTION_BAR_MESSAGES()[i]));
                    });
                    if (++i >= configManager.getPluginConfig().ACTION_BAR_MESSAGES().length)
                        i = 0;
                }
            }.runTaskTimer(plugin, 0L, configManager.getPluginConfig().ACTION_BAR_INTERVAL());
        } else {
            if (actionBarTimerTask != null) {
                actionBarTimerTask.cancel();
                actionBarTimerTask = null;
            }
        }
    }

    @Override
    @Nullable
    public String getPermission() {
        return "ultimatehub.actionbar";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
