package net.kamiland.ultimatehub.module;

import org.jetbrains.annotations.Nullable;

public class JQMessageModule implements Module {

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public void setup() {

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
