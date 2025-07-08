package net.kamiland.ultimatehub.config;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.data.model.spawn.SpawnLocation;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.LinkedList;
import java.util.Objects;

public class SpawnConfig extends Config {

    private final UltimateHub plugin;
    public LinkedList<SpawnLocation> SPAWN_LOCATIONS = new LinkedList<>();

    public SpawnConfig(UltimateHub plugin) {
        super(plugin, "spawns.yml");
        this.plugin = plugin;
    }

    @Override
    public void load() {
        super.load();
        ConfigurationSection section = Objects.requireNonNull(config.getConfigurationSection("spawns"), "The 'spawns' section in spawns.yml must not be null");
        SPAWN_LOCATIONS.clear();
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
            SPAWN_LOCATIONS.add(var);
        }
    }

}
