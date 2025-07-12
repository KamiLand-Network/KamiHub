package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BossBarModule extends EventModule {

    private final UltimateHub plugin;
    private final ConfigManager configManager;

    public BossBarModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "boss-bar");
        this.plugin = plugin;
        this.configManager = configManager;

        setEnabled(configManager.getModuleConfig().IS_BOSSBAR_ENABLED);
    }

    @Override
    public void load() {

    }

    @Override
    public void unload() {

    }

    @Override
    @NotNull
    public String getPermission() {
        return "ultimatehub.boss-bar";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
