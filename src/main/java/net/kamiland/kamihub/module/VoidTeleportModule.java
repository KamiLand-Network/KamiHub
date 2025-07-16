package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VoidTeleportModule extends EventModule {

    private final KamiHub plugin;
    private final ConfigManager configManager;
    private SpawnModule spawnModule;
    private BukkitTask voidCheckTimerTask;

    public VoidTeleportModule(KamiHub plugin) {
        super(plugin, "void-tp");
        this.plugin = plugin;
        this.configManager = plugin.getConfigManager();
    }

    @Override
    protected void load() {
        spawnModule = (SpawnModule) plugin.getModuleManager().getModule("spawn");
        voidCheckTimerTask = plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (player.getLocation().getY() < configManager.getModuleConfig().VOIDTELEPORT_LEVEL) {
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
