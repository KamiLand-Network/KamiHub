package net.kamiland.ultimatehub;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class UltimateHub extends JavaPlugin {

    @Override
    public void onEnable() {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDisable() {

    }

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

}
