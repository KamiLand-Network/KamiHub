package net.kamiland.ultimatehub.config.impl;

import net.kamiland.ultimatehub.config.Config;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public abstract class ConfigImpl implements Config {

    private final File file;
    private FileConfiguration config;

    public ConfigImpl(@NotNull final File file) {
        this.file = file;
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
            e.printStackTrace();
        }
    }

}
