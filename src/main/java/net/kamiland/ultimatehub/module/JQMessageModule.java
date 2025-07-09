package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.jetbrains.annotations.Nullable;

public class JQMessageModule extends EventModule {

    public JQMessageModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "jq-message");
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @Override
    @Nullable
    public String getPermission() {
        return null;
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
