package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.Jukebox;
import org.bukkit.block.data.type.Sign;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AntiUseModule extends EventModule {

    private final ConfigManager configManager;
    private ModuleConfig config;
    private MessageConfig messages;

    public AntiUseModule(KamiHub plugin) {
        super(plugin, "anti-use");
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
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (isEnabled() && config.ANTIUSE_WORLDS.contains(event.getPlayer().getWorld().getName())) {
            if (! (event.getAction().isRightClick() && event.useInteractedBlock().equals(Event.Result.ALLOW) && event.getClickedBlock() != null && isUsable(event.getClickedBlock()))) return;
            if (event.getPlayer().hasPermission(getBypassPermission())) {
                if (config.IS_ANTIUSE_CREATIVE_ONLY) {
                    if (event.getPlayer().getGameMode() == GameMode.CREATIVE)
                        return;
                } else return;
            }
            event.setCancelled(true);
            event.getPlayer().sendMessage(messages.getMessage(event.getPlayer(), "modules.anti-use"));
        }
    }

    private boolean isUsable(@NotNull Block block) {
        BlockData data = block.getBlockData();
        BlockState state = block.getState();

        if (data instanceof Openable
                || data instanceof Powerable) {
            return true;
        }

        if (state instanceof Container) {
            return true;
        }

        return state instanceof Jukebox
                || state instanceof Sign;
    }

    @Override
    @Nullable
    public String getPermission() {
        return null;
    }

    @Override
    @NotNull
    public String getBypassPermission() {
        return "kamihub.anti-use.bypass";
    }

}
