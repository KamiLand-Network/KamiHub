package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import net.kamiland.ultimatehub.util.MessageUtil;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class BossBarModule implements Module, Listener {

    private final UltimateHub plugin;
    private final ConfigManager configManager;
    private final Map<Player, BossBar> playerBossBars = new HashMap<>();
    private final Map<Player, BukkitTask> playerTasks = new HashMap<>();
    private boolean enabled = false;

    public BossBarModule(UltimateHub plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        setEnabled(configManager.getPluginConfig().IS_BOSSBAR_ENABLED);
    }

    @Override
    public String getName() {
        return "bossbar";
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
        if (!enabled) {
            playerTasks.values().forEach(BukkitTask::cancel);
            playerTasks.clear();
            playerBossBars.keySet().forEach(player -> player.hideBossBar(playerBossBars.get(player)));
            playerBossBars.clear();
        } else {
            plugin.getServer().getOnlinePlayers().forEach(this::setBossBar);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        setBossBar(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (playerBossBars.containsKey(event.getPlayer()))
            event.getPlayer().hideBossBar(playerBossBars.get(event.getPlayer()));
        if (playerTasks.containsKey(event.getPlayer()))
            playerTasks.get(event.getPlayer()).cancel();
        playerBossBars.remove(event.getPlayer());
        playerTasks.remove(event.getPlayer());
    }

    private void setBossBar(Player player) {
        if (enabled) {
            if (getPermission() != null && player.hasPermission(getPermission())) {
                BossBar bossBar = BossBar.bossBar(
                        MessageUtil.getMessage(player, configManager.getPluginConfig().BOSSBAR_MESSAGES[0]),
                        configManager.getPluginConfig().BOSSBAR_PROGRESS,
                        BossBar.Color.valueOf(configManager.getPluginConfig().BOSSBAR_COLOR),
                        BossBar.Overlay.valueOf(configManager.getPluginConfig().BOSSBAR_OVERLAY)
                );
                player.showBossBar(bossBar);

                BukkitTask playerTask = new BukkitRunnable() {
                    int i = 0;
                    @Override
                    public void run() {
                        if (++i >= configManager.getPluginConfig().BOSSBAR_MESSAGES.length)
                            i = 0;
                        if (getPermission() != null && player.hasPermission(getPermission())) {
                            bossBar.name(MessageUtil.getMessage(player, configManager.getPluginConfig().BOSSBAR_MESSAGES[i]));
                            bossBar.progress(configManager.getPluginConfig().BOSSBAR_PROGRESS);
                            bossBar.color(BossBar.Color.valueOf(configManager.getPluginConfig().BOSSBAR_COLOR));
                            bossBar.overlay(BossBar.Overlay.valueOf(configManager.getPluginConfig().BOSSBAR_OVERLAY));
                        }
                    }
                }.runTaskTimer(plugin, configManager.getPluginConfig().BOSSBAR_INTERVAL, configManager.getPluginConfig().BOSSBAR_INTERVAL);

                playerBossBars.put(player, bossBar);
                playerTasks.put(player, playerTask);
            }
        }
    }

    @Override
    @Nullable
    public String getPermission() {
        return "ultimatehub.bossbar";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
