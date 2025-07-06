package net.kamiland.ultimatehub.config.impl;

import net.kamiland.ultimatehub.config.PluginConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class PluginConfigImpl extends ConfigImpl implements PluginConfig {

    public PluginConfigImpl(@NotNull File file) {
        super(file);
    }

    @Override
    public String getConfigVersion() {
        return "";
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
