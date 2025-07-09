package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import net.kamiland.ultimatehub.util.MessageUtil;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BroadcastModule extends Module {

    private final UltimateHub plugin;
    private final ConfigManager configManager;
    private BukkitTask broadcastTimerTask;

    public BroadcastModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "broadcast");
        this.plugin = plugin;
        this.configManager = configManager;

        setEnabled(configManager.getPluginConfig().IS_BROADCAST_ENABLED);
    }

    @Override
    protected void load() {
        if (broadcastTimerTask != null) {
            broadcastTimerTask.cancel();
        }
        broadcastTimerTask = new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                plugin.getServer().getOnlinePlayers().forEach(player -> {
                    if (player.hasPermission(getPermission())) {
                        player.sendMessage(MessageUtil.getMessage(player, configManager.getPluginConfig().BROADCAST_MESSAGES[i]));
                    }
                });
                if (++ i >= configManager.getPluginConfig().BROADCAST_MESSAGES.length)
                    i = 0;
            }
        }.runTaskTimer(plugin, 0L, configManager.getPluginConfig().BROADCAST_INTERVAL);
    }

    @Override
    protected void unload() {
        if (broadcastTimerTask != null) {
            broadcastTimerTask.cancel();
            broadcastTimerTask = null;
        }
    }

    @Override
    @NotNull
    public String getPermission() {
        return "ultimatehub.broadcast";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
