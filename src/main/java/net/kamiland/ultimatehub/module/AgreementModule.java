package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.manager.ConfigManager;
import org.jetbrains.annotations.Nullable;

public class AgreementModule extends EventModule {

    public AgreementModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "agreement");
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
