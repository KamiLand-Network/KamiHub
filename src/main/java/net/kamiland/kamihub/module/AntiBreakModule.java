package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiBreakModule extends EventModule {

    private final ConfigManager configManager;
    private final MessageConfig messages;

    public AntiBreakModule(KamiHub plugin) {
        super(plugin, "anti-break");
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
    public void onBlockBreak(BlockBreakEvent event) {
        if (isEnabled() && configManager.getModuleConfig().ANTIBREAK_WORLDS.contains(event.getPlayer().getWorld().getName())) {
            if (event.getPlayer().hasPermission(getBypassPermission())) {
                if (configManager.getModuleConfig().IS_ANTIBREAK_CREATIVE_ONLY) {
                    if (event.getPlayer().getGameMode() == GameMode.CREATIVE)
                        return;
                } else return;
            }
            event.setCancelled(true);
            event.getPlayer().sendMessage(messages.getMessage(event.getPlayer(), "modules.anti-break"));
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
        return "kamihub.anti-break.bypass";
    }

}
