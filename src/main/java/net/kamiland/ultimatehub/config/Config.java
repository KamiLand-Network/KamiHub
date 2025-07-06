package net.kamiland.ultimatehub.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public interface Config {

    File getFile();
    FileConfiguration getConfig();
    void load();
    void save();

}
