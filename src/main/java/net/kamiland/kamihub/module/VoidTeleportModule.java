package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.manager.ConfigManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VoidTeleportModule extends EventModule {

    private final KamiHub plugin;
    private final ConfigManager configManager;

    public VoidTeleportModule(KamiHub plugin, ConfigManager configManager) {
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
