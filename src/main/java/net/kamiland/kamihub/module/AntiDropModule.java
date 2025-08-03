package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiDropModule extends EventModule {

    private final ConfigManager configManager;
    private ModuleConfig config;
    private MessageConfig messages;

    public AntiDropModule(KamiHub plugin) {
        super(plugin, "anti-drop");
        this.configManager = plugin.getConfigManager();
    }

    @Override
    protected void load() {
        config = configManager.getModuleConfig();
        messages = configManager.getMessageConfig();
    }

    @Override
    protected void unload() {
        config = null;
        messages = null;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (isEnabled() && config.ANTIDROP_WORLDS.contains(event.getPlayer().getWorld().getName())) {
            if (event.getPlayer().hasPermission(getBypassPermission())) {
                if (config.IS_ANTIDROP_CREATIVE_ONLY) {
                    if (event.getPlayer().getGameMode() == GameMode.CREATIVE)
                        return;
                } else return;
            }
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
