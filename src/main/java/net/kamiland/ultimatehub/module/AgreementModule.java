package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.config.ModuleConfig;
import net.kamiland.ultimatehub.data.impl.player.RuntimePlayerDataManager;
import net.kamiland.ultimatehub.data.model.player.PlayerData;
import net.kamiland.ultimatehub.manager.ConfigManager;
import net.kamiland.ultimatehub.util.MessageUtil;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AgreementModule extends EventModule {

    private final UltimateHub plugin;
    private final ConfigManager configManager;
    private final RuntimePlayerDataManager runtimePDM;
    public final Map<UUID, Integer> playerTaskMap = new HashMap<>();

    private Book book;

    public AgreementModule(UltimateHub plugin, ConfigManager configManager, RuntimePlayerDataManager runtimePDM) {
        super(plugin, "agreement");

        this.plugin = plugin;
        this.configManager = configManager;
        this.runtimePDM = runtimePDM;

        setEnabled(configManager.getModuleConfig().IS_AGREEMENT_ENABLED);
    }

    @Override
    protected void load() {
        book = generateBook();
    }

    @Override
    protected void unload() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!isEnabled()) return;
        Player player = event.getPlayer();
        ModuleConfig moduleConfig = configManager.getModuleConfig();
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            if (moduleConfig.IS_AGREEMENT_ON_EVERY_JOIN)
                openBook(player);
            else if (! checkIfPlayerAgreed(player) && moduleConfig.IS_AGREEMENT_ON_JOIN)
                openBook(player);
        }, configManager.getModuleConfig().AGREEMENT_DELAY);
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

    private Book generateBook() {
        Component bookTitle = MessageUtil.getMessage("null");
        Component bookAuthor = MessageUtil.getMessage("null");
        Collection<Component> bookPages = new java.util.ArrayList<>();
        ModuleConfig moduleConfig = configManager.getModuleConfig();
        for (String page : moduleConfig.AGREEMENT_PAGES) {
            bookPages.add(MessageUtil.getMessage(page, moduleConfig.AGREEMENT_ACCEPT_BUTTON,
                    moduleConfig.AGREEMENT_REJECT_BUTTON));
        }
        return Book.book(bookTitle, bookAuthor, bookPages);
    }

    private Boolean checkIfPlayerAgreed(Player player) {
        return Objects.requireNonNull(runtimePDM.getPlayerData(player.getUniqueId())).isAgreement();
    }

    private void openBook(Player player) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            player.openBook(book);
        });

        // Schedule a task to kick the player if they do not agree within the timeout
        if (configManager.getModuleConfig().IS_AGREEMENT_KICK_ON_TIMEOUT) {
            int taskId = Bukkit.getScheduler().runTaskLater(plugin, () -> kickPlayer(player), configManager.getModuleConfig().AGREEMENT_TIMEOUT).getTaskId();
            playerTaskMap.put(player.getUniqueId(), taskId);
        }
    }

    private void kickPlayer(Player player) {
        playerTaskMap.remove(player.getUniqueId());
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

        Bukkit.getScheduler().cancelTask(Objects.requireNonNull(playerTaskMap.get(player.getUniqueId())));
        playerTaskMap.remove(player.getUniqueId());
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
    }

    public void onChange() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            runtimePDM.clearAllAgreementStatus();
            runtimePDM.clearAllAgreementStatusFromStorage();

            Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getOnlinePlayers().forEach(this::openBook));
        });
    }

}
