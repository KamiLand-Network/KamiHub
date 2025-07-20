package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiDropModule extends EventModule {

    private final ConfigManager configManager;
    private final MessageConfig messages;

    public AntiDropModule(KamiHub plugin) {
        super(plugin, "anti-drop");
        this.configManager = plugin.getConfigManager();
        this.messages = configManager.getMessageConfig();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (isEnabled() && configManager.getModuleConfig().ANTIDROP_WORLDS.contains(event.getPlayer().getWorld().getName()) && ! event.getPlayer().hasPermission(getBypassPermission())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(messages.getMessage(event.getPlayer(), "modules.anti-drop"));
        }
    }

    @Override
    @Nullable
    public String getPermission() {
        return null;
    }

    @Override
    @NotNull
    public String getBypassPermission() {
        return "kamihub.anti-drop.bypass";
    }

}
