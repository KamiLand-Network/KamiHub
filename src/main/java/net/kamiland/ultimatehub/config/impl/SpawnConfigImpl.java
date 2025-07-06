package net.kamiland.ultimatehub.config.impl;

import net.kamiland.ultimatehub.config.SpawnConfig;
import net.kamiland.ultimatehub.data.model.spawn.SpawnLocation;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.LinkedList;

public class SpawnConfigImpl extends ConfigImpl implements SpawnConfig {

    public SpawnConfigImpl(@NotNull File file) {
        super(file);
    }

    @Override
    public @Nullable LinkedList<SpawnLocation> getSpawnLocations() {
        return null;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public FileConfiguration getConfig() {
        return null;
    }

    @Override
    public void load() {

    }

    @Override
    public void save() {

    }

}
