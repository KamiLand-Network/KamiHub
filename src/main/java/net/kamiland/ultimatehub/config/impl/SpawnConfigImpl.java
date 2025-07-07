package net.kamiland.ultimatehub.config.impl;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.config.SpawnConfig;
import net.kamiland.ultimatehub.data.model.spawn.SpawnLocation;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.Objects;

public class SpawnConfigImpl extends ConfigImpl implements SpawnConfig {

    private final UltimateHub plugin;
    LinkedList<SpawnLocation> spawnLocations = new LinkedList<>();

    public SpawnConfigImpl(UltimateHub plugin) {
        super(plugin, "spawns.yml");
        this.plugin = plugin;
    }

    @Override
    public void load() {
        super.load();
        ConfigurationSection section = Objects.requireNonNull(config.getConfigurationSection("spawns"), "The 'spawns' section in spawns.yml must not be null");
        spawnLocations.clear();
        for (String key : section.getKeys(false)) {
            ConfigurationSection subSection = Objects.requireNonNull(section.getConfigurationSection(key));
            SpawnLocation var = new SpawnLocation(
                    Objects.requireNonNull(subSection.getString("name"), "The 'name' key in spawns.yml must not be null"),
                    new Location(
                            Objects.requireNonNull(
                                    plugin.getServer().getWorld(
                                            Objects.requireNonNull(subSection.getString("world"),
                                                    "The 'world' column must not be null")),
                                    "The specified world not found"),
                            subSection.getDouble("x"),
                            subSection.getDouble("y"),
                            subSection.getDouble("z"),
                            (float) subSection.getDouble("yaw"),
                            (float) subSection.getDouble("pitch"))
            );
            spawnLocations.add(var);
        }
    }

    @Override
    @Nullable
    public LinkedList<SpawnLocation> getSpawnLocations() {
        return spawnLocations;
    }

}
