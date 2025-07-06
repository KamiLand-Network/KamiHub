package net.kamiland.ultimatehub.module;

import org.jetbrains.annotations.Nullable;

public interface Module {

    default boolean isEnabled() { return false; }
    void setEnabled(boolean enabled);
    @Nullable String getPermission();
    @Nullable String getBypassPermission();

}
