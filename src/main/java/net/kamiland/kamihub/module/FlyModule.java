package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ModuleConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlyModule extends EventModule {

    private final ModuleConfig config;
    private final List<String> worlds;
    private final Map<Player, Boolean> playerFlyMap = new HashMap<>();

    public FlyModule(KamiHub plugin) {
        super(plugin, "fly");
        this.worlds = plugin.getConfigManager().getModuleConfig().FLY_WORLDS;
        this.config = plugin.getConfigManager().getModuleConfig();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

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

    @EventHandler
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
