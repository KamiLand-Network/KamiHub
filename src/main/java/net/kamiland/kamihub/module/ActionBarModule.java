package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import net.kamiland.kamihub.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionBarModule extends Module {

    private final KamiHub plugin;
    private final ConfigManager configManager;
    private BukkitTask actionBarTimerTask;

    public ActionBarModule(KamiHub plugin) {
        super(plugin, "action-bar");
        this.plugin = plugin;
        this.configManager = plugin.getConfigManager();

        setEnabled(configManager.getModuleConfig().IS_ACTIONBAR_ENABLED);
    }

    @Override
    protected void load() {
        if (actionBarTimerTask != null) {
            actionBarTimerTask.cancel();
        }
        ModuleConfig config = configManager.getModuleConfig();
        actionBarTimerTask = new BukkitRunnable() {
            final Map<World, Integer> worldsMap = new HashMap<>();
            final Map<String, List<String>> actionBarWorlds = config.ACTIONBAR_WORLDS;

            {
                for (String worldName : actionBarWorlds.keySet()) {
                    World world = Bukkit.getWorld(worldName);
                    if (world != null) {
                        worldsMap.put(world, 0);
                    }
                }
            }

            @Override
            public void run() {
                for (Map.Entry<World, Integer> entry : worldsMap.entrySet()) {
                    World world = entry.getKey();
                    int index = entry.getValue();
                    List<String> messages = actionBarWorlds.get(world.getName());

                    if (messages != null && !messages.isEmpty()) {
                        String message = messages.get(index);
                        world.getPlayers().stream()
                                .filter(player -> player.hasPermission(getPermission()))
                                .forEach(player -> player.sendActionBar(MessageUtil.getMessage(player, message)));

                        index = (index + 1) % messages.size();
                        worldsMap.put(world, index);
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, config.ACTIONBAR_INTERVAL);
    }

    @Override
    protected void unload() {
        if (actionBarTimerTask != null) {
            actionBarTimerTask.cancel();
            actionBarTimerTask = null;
        }
    }

    @Override
    @NotNull
    public String getPermission() {
        return "kamihub.action-bar";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
