package net.kamiland.ultimatehub.config.impl;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.config.Config;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public abstract class ConfigImpl implements Config {

    protected final UltimateHub plugin;
    protected final File file;
    protected FileConfiguration config;

    public ConfigImpl(UltimateHub plugin, String path) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), path);
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    @Nullable
    public FileConfiguration getConfig() {
        return config;
    }

    @Override
    public void load() {
        config = YamlConfiguration.loadConfiguration(this.file);
    }

    @Override
    public void save() {
        try {
            config.save(this.file);
        } catch (IOException e) {
            plugin.getSLF4JLogger().error(e.getLocalizedMessage());
        }
    }

}
