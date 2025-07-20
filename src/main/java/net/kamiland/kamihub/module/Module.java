package net.kamiland.kamihub.module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.util.MessageUtil;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public abstract class Module {

    protected final KamiHub plugin;
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

    public Component toComponent() {
        String hoverText = String.format(
                "<gray>enabled: %s",
                enabled ? "<green>true" : "<red>false"
        );
        String color = enabled ? "<green>" : "<red>";
        return MessageUtil.getMessage("<hover:show_text:'" + hoverText + "'>" + color + name + "</hover>");
    }

}
