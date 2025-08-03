package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import net.kamiland.kamihub.util.MessageUtil;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BossBarModule extends EventModule {

    private final ConfigManager configManager;
    private BukkitTask bossBarTimerTask;
    private final Map<World, Integer> worldsMap = new HashMap<>();
    private final Map<Player, BossBar> playerBossBarMap = new HashMap<>();

    public BossBarModule(KamiHub plugin) {
        super(plugin, "boss-bar");
        this.configManager = plugin.getConfigManager();
    }

    @Override
    public void load() {
        ModuleConfig config = configManager.getModuleConfig();
        bossBarTimerTask = new BukkitRunnable() {
            {
                for (String worldName : config.BOSSBAR_WORLDS.keySet()) {
                    World world = Bukkit.getWorld(worldName);
                    if (world != null) {
                        worldsMap.put(world, 0);
                    }
                }
            }

            @Override
            public void run() {
                for (Map.Entry<World, Integer> entry : worldsMap.entrySet()) {
                    World world = entry.getKey();
                    int index = entry.getValue();
                    List<String> bars = config.BOSSBAR_WORLDS.get(world.getName());

                    if (bars != null && !bars.isEmpty()) {
                        String raw = bars.get(index);
                        String name = raw.split(":")[3];
                        float progress = Float.parseFloat(raw.split(":")[0]);
                        BossBar.Color color = BossBar.Color.valueOf(raw.split(":")[1]);
                        BossBar.Overlay overlay = BossBar.Overlay.valueOf(raw.split(":")[2]);
                        world.getPlayers()
                                .forEach(player -> {
                                    if (player.hasPermission(getPermission())) {
                                        if (playerBossBarMap.containsKey(player)) {
                                            BossBar bossBar = playerBossBarMap.get(player);
                                            bossBar.name(MessageUtil.getMessage(player, name));
                                            bossBar.progress(progress);
                                            bossBar.color(color);
                                            bossBar.overlay(overlay);
                                        } else {
                                            BossBar bossBar = BossBar.bossBar(MessageUtil.getMessage(player, name), progress, color, overlay);
                                            player.showBossBar(bossBar);
                                            playerBossBarMap.put(player, bossBar);
                                        }
                                    } else {
                                        if (playerBossBarMap.containsKey(player)) {
                                            BossBar bossBar = playerBossBarMap.get(player);
                                            player.hideBossBar(bossBar);
                                            playerBossBarMap.remove(player);
                                        }
                                    }
                                });
                        playerBossBarMap.entrySet().removeIf(entry1 -> !entry1.getKey().isOnline());

                        index = (index + 1) % bars.size();
                        worldsMap.put(world, index);
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, config.BOSSBAR_INTERVAL);
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (isEnabled() && ! event.getFrom().getWorld().equals(event.getTo().getWorld())) {
            if (! worldsMap.containsKey(event.getTo().getWorld()))
                event.getPlayer().hideBossBar(playerBossBarMap.get(event.getPlayer()));
            else
                event.getPlayer().showBossBar(playerBossBarMap.get(event.getPlayer()));
        }
    }

    @Override
    public void unload() {
        playerBossBarMap.forEach(Audience::hideBossBar);
        playerBossBarMap.clear();
        worldsMap.clear();
        if (bossBarTimerTask!= null) {
            bossBarTimerTask.cancel();
            bossBarTimerTask = null;
        }
    }

    @Override
    @NotNull
    public String getPermission() {
        return "kamihub.boss-bar";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
