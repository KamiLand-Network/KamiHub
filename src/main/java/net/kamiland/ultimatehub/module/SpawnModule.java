package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.data.model.spawn.SpawnLocation;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

import static net.kamiland.ultimatehub.config.MessageConfig.Message.SPAWN_TELEPORT;

public class SpawnModule extends EventModule {

    private final UltimateHub plugin;
    private final ConfigManager configManager;

    public SpawnModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "spawn");
        this.plugin = plugin;
        this.configManager = configManager;

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
        Random random = new Random();
        int index = random.nextInt(locations.size());
        player.teleport(locations.get(index).getLocation());
        player.sendMessage(configManager.getMessageConfig().getMessage(player, SPAWN_TELEPORT));
    }

    @Override
    @NotNull
    public String getPermission() {
        return "ultimatehub.spawn";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return "";
    }

}
