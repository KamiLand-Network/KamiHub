package net.kamiland.kamihub.config;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.data.model.spawn.SpawnLocation;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
                    Objects.requireNonNull(subSection.getString("name"), "The 'name' key in spawns.yml must not be null").toLowerCase(Locale.ROOT),
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
            if (SPAWN_LOCATIONS.stream().noneMatch(loc -> loc.getName().equals(var.getName())))
                SPAWN_LOCATIONS.add(var);
        }
        save();
    }

    @Override
    public void save() {
        config.getKeys(true).forEach(key -> config.set(key, null));
        SPAWN_LOCATIONS.forEach(loc -> {
            config.set("spawns." + loc.getName() + ".name", loc.getName());
            config.set("spawns." + loc.getName() + ".x", loc.getLocation().getX());
            config.set("spawns." + loc.getName() + ".y", loc.getLocation().getY());
            config.set("spawns." + loc.getName() + ".z", loc.getLocation().getZ());
            config.set("spawns." + loc.getName() + ".yaw", loc.getLocation().getYaw());
            config.set("spawns." + loc.getName() + ".pitch", loc.getLocation().getPitch());
            config.set("spawns." + loc.getName() + ".world", loc.getLocation().getWorld().getName());
        });

        super.save();
    }

}
