package net.kamiland.kamihub.action;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@Getter
public abstract class Action {

    protected final String prefix;

    protected Action(String prefix) {
        this.prefix = prefix;
    }

    public abstract void resolve(@Nullable Player player, String value);

}
