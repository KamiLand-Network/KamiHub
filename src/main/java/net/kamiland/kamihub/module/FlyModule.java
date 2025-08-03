package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlyModule extends EventModule {

    private final Map<Player, Boolean> playerFlyMap = new HashMap<>();
    private ModuleConfig config;
    private List<String> worlds;

    public FlyModule(KamiHub plugin) {
        super(plugin, "fly");
    }

    @Override
    protected void load() {
        config = plugin.getConfigManager().getModuleConfig();
        worlds = config.FLY_WORLDS;
    }

    @Override
    protected void unload() {
        playerFlyMap.keySet().forEach(player -> {
            player.setAllowFlight(false);
            player.setFlying(false);
        });
        playerFlyMap.clear();
        worlds.clear();
        config = null;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (config.IS_FLY_ENABLED) {
            Player player = event.getPlayer();
            if (! player.hasPermission(getPermission()) || ! worlds.contains(player.getWorld().getName())) {
                playerFlyMap.put(player, false);
                return;
            }
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.setAllowFlight(true);
                if (config.IS_FLY_AUTO_FLY_ON_JOIN) {
                    player.setFlying(true);
                }
            }, 5L);
            playerFlyMap.put(player, true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        playerFlyMap.remove(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (! config.IS_FLY_ENABLED || event.getFrom().getWorld().equals(event.getTo().getWorld())) return;
        Player player = event.getPlayer();
        if (! worlds.contains(player.getWorld().getName())) {
            player.setAllowFlight(false);
            player.setFlying(false);
        } else {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.setAllowFlight(true);
                if (config.IS_FLY_AUTO_FLY_ON_JOIN) {
                    player.setFlying(true);
                }
            }, 5L);
        }
    }

    public boolean isAllowFly(Player player) {
        return playerFlyMap.getOrDefault(player, false);
    }

    public void setAllowFly(Player player, boolean allow) {
        playerFlyMap.put(player, allow);
        if (allow) {
            player.setAllowFlight(true);
            player.setFlying(true);
        } else {
            player.setFlying(false);
            player.setAllowFlight(false);
        }
    }

    @Override
    @NotNull
    public String getPermission() {
        return "kamihub.fly";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
