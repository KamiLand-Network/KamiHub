package net.kamiland.ultimatehub.config.impl;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.config.PluginConfig;

public class PluginConfigImpl extends ConfigImpl implements PluginConfig {

    private String CONFIG_VERSION;

    public PluginConfigImpl(UltimateHub plugin) {
        super(plugin, "config.yml");
    }

    @Override
    public void load() {
        super.load();
        CONFIG_VERSION = config.getString("config-version", "1.0");
    }

    @Override
    public String getConfigVersion() {
        return CONFIG_VERSION;
    }

}
