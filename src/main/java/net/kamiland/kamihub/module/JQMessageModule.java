package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import net.kamiland.kamihub.manager.ServerManager;
import net.kamiland.kamihub.util.MessageUtil;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.UserManager;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class JQMessageModule extends EventModule {

    private final KamiHub plugin;
    private final ConfigManager configManager;
    private Map<String, Map<String, List<String>>> groupMessages;
    private Map<String, Integer> groupPriority;
    private UserManager lpUserManager;
    private Permission vaultPermission;

    public JQMessageModule(KamiHub plugin) {
        super(plugin, "jq-message");
        this.plugin = plugin;
        this.configManager = plugin.getConfigManager();
    }

    @Override
    protected void load() {
        ModuleConfig config = configManager.getModuleConfig();
        groupMessages = config.JQMESSAGE_GROUPS;
        groupPriority = config.JQMESSAGE_GROUPS_PRIORITY;

        if (ServerManager.LUCKPERMS) {
            lpUserManager = LuckPermsProvider.get().getUserManager();
        } else if (ServerManager.VAULT) {
            RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
            if (rsp != null) vaultPermission = rsp.getProvider();
        }
    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (! isEnabled()) return;
        Player player = event.getPlayer();
        List<String> messages = groupMessages.get(getGroup(player)).get("join");
        if (messages != null && ! messages.isEmpty()) {
            event.joinMessage(MessageUtil.getMessage(player, messages.get(new Random().nextInt(messages.size())), player.getName()));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (! isEnabled()) return;
        Player player = event.getPlayer();
        List<String> messages = groupMessages.get(getGroup(player)).get("quit");
        if (messages != null && ! messages.isEmpty()) {
            event.quitMessage(MessageUtil.getMessage(player, messages.get(new Random().nextInt(messages.size())), player.getName()));
        }
    }

    @Override
    @Nullable
    public String getPermission() {
        return null;
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

    private String getGroup(Player player) {
        AtomicReference<String> group = new AtomicReference<>("default");
        if (ServerManager.LUCKPERMS) {
            group.set(Objects.requireNonNull(lpUserManager.getUser(player.getUniqueId())).getPrimaryGroup());
        } else if (ServerManager.VAULT) {
            group.set(vaultPermission.getPrimaryGroup(player));
        } else {
            AtomicInteger priority = new AtomicInteger();
            groupPriority.forEach((g, p) -> {
                if (player.hasPermission("kamihub.group." + g) && p > priority.get()) {
                    group.set(g);
                    priority.set(p);
                }
            });
        }
        return group.get();
    }

}
