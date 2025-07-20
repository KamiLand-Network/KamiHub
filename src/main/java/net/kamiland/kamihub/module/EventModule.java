package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import org.bukkit.event.Listener;

public abstract class EventModule extends Module implements Listener {

    public EventModule(KamiHub plugin, String name) {
        super(plugin, name);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

}
