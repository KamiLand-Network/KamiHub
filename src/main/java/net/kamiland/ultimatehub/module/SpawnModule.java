package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.data.model.spawn.SpawnLocation;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

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
        if (!configManager.getModuleConfig().IS_SPAWN_ON_JOIN) return;
        List<SpawnLocation> locations = configManager.getSpawnConfig().SPAWN_LOCATIONS;
        Random random = new Random();
        int index = random.nextInt(locations.size());
        event.getPlayer().teleport(locations.get(index).getLocation());
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
