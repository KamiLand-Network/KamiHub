package net.kamiland.ultimatehub.module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kamiland.ultimatehub.UltimateHub;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public abstract class Module {

    protected final UltimateHub plugin;
    @Getter
    protected final String name;
    @Getter
    private boolean enabled = false;

    public void setEnabled(boolean enabled) {
        if (enabled == this.enabled) return;
        this.enabled = enabled;
        if (enabled) load();
        else unload();
    }

    protected abstract void load();

    protected abstract void unload();

    @Nullable
    public abstract String getPermission();

    @Nullable
    public abstract String getBypassPermission();

}
