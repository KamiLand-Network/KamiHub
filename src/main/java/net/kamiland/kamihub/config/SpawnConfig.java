package net.kamiland.kamihub.config;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.data.model.spawn.SpawnLocation;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpawnConfig extends Config {

    private final KamiHub plugin;
    public List<SpawnLocation> SPAWN_LOCATIONS = new ArrayList<>();

    public SpawnConfig(KamiHub plugin) {
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
