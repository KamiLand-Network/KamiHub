package net.kamiland.ultimatehub.config;

import net.kamiland.ultimatehub.UltimateHub;

public class PluginConfig extends Config {

    public static String CONFIG_VERSION;


    public PluginConfig(UltimateHub plugin) {
        super(plugin, "config.yml");
    }

    @Override
    public void load() {
        super.load();
        CONFIG_VERSION = config.getString("version");
    }

}
