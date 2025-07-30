package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.api.event.AgreementChangeEvent;
import net.kamiland.kamihub.api.event.PlayerAgreementEvent;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.data.impl.player.RuntimePlayerDataManager;
import net.kamiland.kamihub.data.model.player.PlayerData;
import net.kamiland.kamihub.manager.ConfigManager;
import net.kamiland.kamihub.util.MessageUtil;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.*;

public class AgreementModule extends EventModule {

    private final ConfigManager configManager;
    private final RuntimePlayerDataManager runtimePDM;
    private final ModuleConfig moduleConfig;
    public final Map<UUID, Integer> playerTaskMap = new HashMap<>();

    public AgreementModule(KamiHub plugin) {
        super(plugin, "agreement");

        this.configManager = plugin.getConfigManager();
        this.runtimePDM = plugin.getRuntimePDM();
        this.moduleConfig = configManager.getModuleConfig();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!isEnabled()) return;
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            if (! player.isOnline()) return;
            boolean isPlayerAccepted = isPlayerAccepted(player);
            if (moduleConfig.IS_AGREEMENT_ON_EVERY_JOIN) {
                openAgreementBook(player);
                if (configManager.getModuleConfig().IS_AGREEMENT_KICK_ON_TIMEOUT)
                    scheduleKickTask(player);
            } else if (moduleConfig.IS_AGREEMENT_ON_JOIN) {
                if (isPlayerAccepted) return;
                openAgreementBook(player);
                if (configManager.getModuleConfig().IS_AGREEMENT_KICK_ON_TIMEOUT)
                    scheduleKickTask(player);
            }
        }, configManager.getModuleConfig().AGREEMENT_DELAY);

        new BukkitRunnable() {
            int remaining = moduleConfig.AGREEMENT_TIMEOUT / 20;
            @Override
            public void run() {
                if (moduleConfig.IS_AGREEMENT_SHOW_TITLE && player.isOnline() && ! isPlayerAccepted(player)) {
                    Title title = Title.title(
                            MessageUtil.getMessage(player, moduleConfig.AGREEMENT_TITLE, player.getName(), String.valueOf(remaining)),
                            MessageUtil.getMessage(player, moduleConfig.AGREEMENT_SUBTITLE, player.getName(), String.valueOf(remaining)),
                            Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(1), Duration.ofMillis(500))
                    );
                    player.showTitle(title);
                } else {
                    this.cancel();
                }
                remaining --;
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (moduleConfig.IS_AGREEMENT_REOPEN_ON_MOVE) {
            Player player = event.getPlayer();
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                if (! isPlayerAccepted(player)) {
                    openAgreementBook(player);
                }
            });
        }
    }

    private Book generateBook(Player player) {
        Component bookTitle = MessageUtil.getMessage("null");
        Component bookAuthor = MessageUtil.getMessage("null");
        Collection<Component> bookPages = new java.util.ArrayList<>();
        ModuleConfig moduleConfig = configManager.getModuleConfig();
        for (String page : moduleConfig.AGREEMENT_PAGES) {
            bookPages.add(MessageUtil.getMessage(player, page, moduleConfig.AGREEMENT_ACCEPT_BUTTON,
                    moduleConfig.AGREEMENT_REJECT_BUTTON));
        }
        return Book.book(bookTitle, bookAuthor, bookPages);
    }

    private Boolean isPlayerAccepted(Player player) {
        PlayerData playerData = runtimePDM.getPlayerData(player.getUniqueId());
        if (playerData == null) {
            playerData = runtimePDM.getPlayerDataFromStorage(player.getUniqueId());
        }
        if (playerData != null)
            return playerData.isAgreement();
        plugin.getSLF4JLogger().error("Can't get player data for {}, is the connection pool suffering from high concurrent queries?", player.getName());
        return false;
    }

    public void openAgreementBook(Player player) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            player.openBook(generateBook(player));
        });
    }

    public void scheduleKickTask(Player player) {
        int taskId = Bukkit.getScheduler().runTaskLater(plugin, () -> kickPlayer(player), configManager.getModuleConfig().AGREEMENT_TIMEOUT).getTaskId();
        playerTaskMap.put(player.getUniqueId(), taskId);
    }

    private void kickPlayer(Player player) {
        playerTaskMap.remove(player.getUniqueId());
        if (player.isOnline())
            player.kick(MessageUtil.getMessage(configManager.getModuleConfig().AGREEMENT_KICK_MESSAGE));
    }

    public void onAccept(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            PlayerData playerData = runtimePDM.getPlayerData(player.getUniqueId());
            if (playerData != null) {
                playerData.setAgreement(true);
                runtimePDM.savePlayer(player.getUniqueId(), playerData);
                runtimePDM.savePlayerToStorage(player.getUniqueId());
            }
        });

        if (playerTaskMap.get(player.getUniqueId()) != null)
            Bukkit.getScheduler().cancelTask(playerTaskMap.get(player.getUniqueId()));
        playerTaskMap.remove(player.getUniqueId());
        Arrays.stream(configManager.getModuleConfig().AGREEMENT_ACCEPT_ACTIONS).forEach(action -> plugin.getActionResolver().resolve(player, action));

        plugin.getServer().getPluginManager().callEvent(new PlayerAgreementEvent(player, true));
    }

    public void onReject(Player player) {
        if (configManager.getModuleConfig().IS_AGREEMENT_KICK_ON_REJECT)
            kickPlayer(player);
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            PlayerData playerData = runtimePDM.getPlayerDataFromStorage(player.getUniqueId());
            if (playerData != null && playerData.isAgreement()) {
                playerData.setAgreement(false);
                if (player.isOnline()) runtimePDM.savePlayer(player.getUniqueId(), playerData);
                runtimePDM.savePlayerToStorage(player.getUniqueId(), playerData);
            }
        });
        Arrays.stream(configManager.getModuleConfig().AGREEMENT_REJECT_ACTIONS).forEach(action -> plugin.getActionResolver().resolve(player, action));

        plugin.getServer().getPluginManager().callEvent(new PlayerAgreementEvent(player, false));
    }

    public void onChange() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            runtimePDM.clearAllAgreementStatus();
            runtimePDM.clearAllAgreementStatusFromStorage();

            Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getOnlinePlayers().forEach(this::openAgreementBook));
        });

        plugin.getServer().getPluginManager().callEvent(new AgreementChangeEvent());
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

}
