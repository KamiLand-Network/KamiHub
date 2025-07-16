package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.data.model.spawn.SpawnLocation;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class SpawnModule extends EventModule {

    private final ConfigManager configManager;

    public SpawnModule(KamiHub plugin) {
        super(plugin, "spawn");
        this.configManager = plugin.getConfigManager();

        setEnabled(configManager.getModuleConfig().IS_SPAWN_ENABLED);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!isEnabled()) return;
        if (configManager.getModuleConfig().IS_SPAWN_ON_JOIN && event.getPlayer().hasPermission(getPermission())) spawn(event.getPlayer());
    }

    public void spawn(Player player) {
        List<SpawnLocation> locations = configManager.getSpawnConfig().SPAWN_LOCATIONS;
        if (locations == null || locations.isEmpty()) {
            if (player.getRespawnLocation() != null) {
                player.teleport(player.getRespawnLocation());
            } else {
                player.teleport(player.getWorld().getSpawnLocation());
            }
        } else {
            Random random = new Random();
            int index = random.nextInt(locations.size());
            player.teleport(locations.get(index).getLocation());
        }
        player.sendMessage(configManager.getMessageConfig().getMessage(player, "modules.spawn.teleport"));
    }

    @Override
    @NotNull
    public String getPermission() {
        return "kamihub.spawn";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
