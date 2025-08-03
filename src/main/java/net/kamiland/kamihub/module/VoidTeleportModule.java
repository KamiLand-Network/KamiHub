package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VoidTeleportModule extends EventModule {

    private final ConfigManager configManager;
    private ModuleConfig config;
    private MessageConfig messages;
    private SpawnModule spawnModule;
    private BukkitTask voidCheckTimerTask;

    public VoidTeleportModule(KamiHub plugin) {
        super(plugin, "void-teleport");
        this.configManager = plugin.getConfigManager();
    }

    @Override
    protected void load() {
        config = configManager.getModuleConfig();
        messages = configManager.getMessageConfig();

        spawnModule = (SpawnModule) plugin.getModuleManager().getModule("spawn");
        voidCheckTimerTask = plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (player.getLocation().getY() < config.VOIDTELEPORT_LEVEL) {
                    player.sendMessage(messages.getMessage(player, "modules.void-tp"));
                    spawnModule.spawn(player);
                }
            }
        }, 10L, 10L);
    }

    @Override
    protected void unload() {
        voidCheckTimerTask.cancel();
    }

    @Override
    @NotNull
    public String getPermission() {
        return "kamihub.void-tp";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
