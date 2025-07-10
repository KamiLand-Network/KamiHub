package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import net.kamiland.ultimatehub.util.MessageUtil;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class BossBarModule extends EventModule {

    private final UltimateHub plugin;
    private final ConfigManager configManager;
    private final Map<Player, BossBar> bossBarMap = new HashMap<>();
    private final Map<Player, Integer> bossBarIndexMap = new HashMap<>();
    private BukkitTask timerTask;

    public BossBarModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "boss-bar");
        this.plugin = plugin;
        this.configManager = configManager;

        setEnabled(configManager.getPluginConfig().IS_BOSSBAR_ENABLED);
    }

    @Override
    public void load() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            bossBarMap.put(player, null);
            bossBarIndexMap.put(player, 0);
        }

        timerTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : bossBarMap.keySet()) {
                    if (!player.hasPermission(getPermission())) {
                        if (bossBarMap.get(player) != null)
                            player.hideBossBar(bossBarMap.get(player));
                        return;
                    }
                    BossBar bossBar = bossBarMap.get(player);
                    int i = bossBarIndexMap.get(player);
                    if (bossBar != null) {
                        bossBar.name(MessageUtil.getMessage(player, configManager.getPluginConfig().BOSSBAR_MESSAGES[i]));
                        bossBar.progress(configManager.getPluginConfig().BOSSBAR_PROGRESS);
                        bossBar.color(BossBar.Color.valueOf(configManager.getPluginConfig().BOSSBAR_COLOR));
                        bossBar.overlay(BossBar.Overlay.valueOf(configManager.getPluginConfig().BOSSBAR_OVERLAY));
                    } else {
                        bossBar = BossBar.bossBar(
                                MessageUtil.getMessage(player, configManager.getPluginConfig().BOSSBAR_MESSAGES[i]),
                                configManager.getPluginConfig().BOSSBAR_PROGRESS,
                                BossBar.Color.valueOf(configManager.getPluginConfig().BOSSBAR_COLOR),
                                BossBar.Overlay.valueOf(configManager.getPluginConfig().BOSSBAR_OVERLAY)
                        );
                    }
                    player.showBossBar(bossBar);
                    if (++ i >= configManager.getPluginConfig().BOSSBAR_MESSAGES.length)
                        i = 0;
                    bossBarMap.put(player, bossBar);
                    bossBarIndexMap.put(player, i);
                }
            }
        }.runTaskTimer(plugin, 0, configManager.getPluginConfig().BOSSBAR_INTERVAL);
    }

    @Override
    public void unload() {
        timerTask.cancel();
        bossBarMap.forEach((player, bossBar) -> {
            if (bossBar!= null)
                player.hideBossBar(bossBar);
        });
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        bossBarMap.put(event.getPlayer(), null);
        bossBarIndexMap.put(event.getPlayer(), 0);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        bossBarMap.remove(event.getPlayer());
    }

    @Override
    @NotNull
    public String getPermission() {
        return "ultimatehub.boss-bar";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
