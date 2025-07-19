package net.kamiland.kamihub.action;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@Getter
public abstract class Action {

    protected final String prefix;
    protected final String prefixRegex;

    protected Action(String prefix, String prefixRegex) {
        this.prefix = prefix;
        this.prefixRegex = prefixRegex;
    }

    public abstract void resolve(@Nullable Player player, String value);

}
