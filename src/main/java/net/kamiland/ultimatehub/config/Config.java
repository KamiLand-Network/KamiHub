package net.kamiland.ultimatehub.config;

import lombok.Getter;
import net.kamiland.ultimatehub.UltimateHub;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public abstract class Config {

    protected final UltimateHub plugin;
    @Getter
    protected final File file;
    protected FileConfiguration config;

    public Config(UltimateHub plugin, String path) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), path);
        plugin.saveResource(path, false);
    }

    @Nullable
    public FileConfiguration getConfig() {
        return config;
    }

    public void load() {
        config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void save() {
        try {
            config.save(this.file);
        } catch (IOException e) {
            plugin.getSLF4JLogger().error(e.getLocalizedMessage());
        }
    }

}
