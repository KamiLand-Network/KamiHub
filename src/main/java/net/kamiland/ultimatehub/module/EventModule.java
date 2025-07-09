package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import org.bukkit.event.Listener;

public abstract class EventModule extends Module implements Listener {

    public EventModule(UltimateHub plugin, String name) {
        super(plugin, name);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

}
