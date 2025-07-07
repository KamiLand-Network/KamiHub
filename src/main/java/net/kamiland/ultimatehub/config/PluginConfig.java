package net.kamiland.ultimatehub.config;

public interface PluginConfig extends Config {

    String getConfigVersion();
    boolean IS_ACTIONBAR_ENABLED();
    int ACTION_BAR_INTERVAL();
    String[] ACTION_BAR_MESSAGES();

}
