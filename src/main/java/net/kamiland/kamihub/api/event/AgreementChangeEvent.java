package net.kamiland.kamihub.api.event;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class AgreementChangeEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

}
