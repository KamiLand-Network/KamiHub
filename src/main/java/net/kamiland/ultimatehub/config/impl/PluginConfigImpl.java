package net.kamiland.ultimatehub.config.impl;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.config.PluginConfig;

public class PluginConfigImpl extends ConfigImpl implements PluginConfig {

    private String CONFIG_VERSION;
    private boolean IS_ACTIONBAR_ENABLED;
    private int ACTION_BAR_INTERVAL;
    private String[] ACTION_BAR_MESSAGES;

    public PluginConfigImpl(UltimateHub plugin) {
        super(plugin, "config.yml");
    }

    @Override
    public void load() {
        super.load();
        CONFIG_VERSION = config.getString("config-version", "1.0");
        IS_ACTIONBAR_ENABLED = config.getBoolean("modules.action-bar.enabled", false);
        ACTION_BAR_INTERVAL = config.getInt("modules.action-bar.interval", 1200);
        ACTION_BAR_MESSAGES = config.getStringList("modules.action-bar.messages").toArray(new String[0]);
    }

    @Override
    public String getConfigVersion() {
        return CONFIG_VERSION;
    }

    @Override
    public boolean IS_ACTIONBAR_ENABLED() {
        return IS_ACTIONBAR_ENABLED;
    }

    @Override
    public int ACTION_BAR_INTERVAL() {
        return ACTION_BAR_INTERVAL;
    }

    @Override
    public String[] ACTION_BAR_MESSAGES() {
        return ACTION_BAR_MESSAGES;
    }

}
