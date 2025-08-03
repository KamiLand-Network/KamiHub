package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import net.kamiland.kamihub.util.MessageUtil;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BroadcastModule extends Module {

    private final ConfigManager configManager;
    private BukkitTask broadcastTimerTask;
    private ModuleConfig config;

    public BroadcastModule(KamiHub plugin) {
        super(plugin, "broadcast");
        this.configManager = plugin.getConfigManager();
    }

    @Override
    protected void load() {
        config = configManager.getModuleConfig();

        if (broadcastTimerTask != null) {
            broadcastTimerTask.cancel();
        }
        broadcastTimerTask = new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                plugin.getServer().getOnlinePlayers().forEach(player -> {
                    if (player.hasPermission(getPermission())) {
                        player.sendMessage(MessageUtil.getMessage(player, config.BROADCAST_MESSAGES[i]));
                    }
                });
                if (++ i >= config.BROADCAST_MESSAGES.length)
                    i = 0;
            }
        }.runTaskTimer(plugin, 0L, config.BROADCAST_INTERVAL);
    }

    @Override
    protected void unload() {
        config = null;

        if (broadcastTimerTask != null) {
            broadcastTimerTask.cancel();
            broadcastTimerTask = null;
        }
    }

    @Override
    @NotNull
    public String getPermission() {
        return "kamihub.broadcast.notify";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
