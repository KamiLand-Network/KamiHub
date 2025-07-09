package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VoidTeleportModule extends EventModule {

    private final UltimateHub plugin;
    private final ConfigManager configManager;

    public VoidTeleportModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "void-tp");
        this.plugin = plugin;
        this.configManager = configManager;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @Override
    @NotNull
    public String getPermission() {
        return "ultimatehub.void-tp";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return "";
    }

}
