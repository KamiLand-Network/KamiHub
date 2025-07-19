package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VoidTeleportModule extends EventModule {

    private final ConfigManager configManager;
    private final MessageConfig messages;
    private SpawnModule spawnModule;
    private BukkitTask voidCheckTimerTask;

    public VoidTeleportModule(KamiHub plugin) {
        super(plugin, "void-tp");
        this.configManager = plugin.getConfigManager();
        this.messages = configManager.getMessageConfig();
    }

    @Override
    protected void load() {
        spawnModule = (SpawnModule) plugin.getModuleManager().getModule("spawn");
        voidCheckTimerTask = plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (player.getLocation().getY() < configManager.getModuleConfig().VOIDTELEPORT_LEVEL) {
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
