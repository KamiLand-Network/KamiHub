package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import net.kamiland.ultimatehub.util.MessageUtil;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ActionBarModule extends Module {

    private final UltimateHub plugin;
    private final ConfigManager configManager;
    private BukkitTask actionBarTimerTask;

    public ActionBarModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "actionbar");
        this.plugin = plugin;
        this.configManager = configManager;

        setEnabled(configManager.getPluginConfig().IS_ACTIONBAR_ENABLED);
    }

    @Override
    protected void load() {
        if (actionBarTimerTask != null) {
            actionBarTimerTask.cancel();
        }
        actionBarTimerTask = new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                plugin.getServer().getOnlinePlayers().forEach(player -> {
                    if (player.hasPermission(getPermission()))
                        player.sendActionBar(MessageUtil.getMessage(player, configManager.getPluginConfig().ACTIONBAR_MESSAGES[i]));
                });
                if (++i >= configManager.getPluginConfig().ACTIONBAR_MESSAGES.length)
                    i = 0;
            }
        }.runTaskTimer(plugin, 0L, configManager.getPluginConfig().ACTIONBAR_INTERVAL);
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
        return "ultimatehub.actionbar";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
