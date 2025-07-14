package net.kamiland.ultimatehub.module;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.config.ModuleConfig;
import net.kamiland.ultimatehub.manager.ConfigManager;
import net.kamiland.ultimatehub.util.MessageUtil;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AgreementModule extends EventModule {

    private final UltimateHub plugin;
    private final ConfigManager configManager;
    public final Map<UUID, Integer> playerTaskMap = new HashMap<>();

    private Book book;

    public AgreementModule(UltimateHub plugin, ConfigManager configManager) {
        super(plugin, "agreement");

        this.plugin = plugin;
        this.configManager = configManager;

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
        if (moduleConfig.IS_AGREEMENT_ON_EVERY_JOIN)
            openBook(player);
        else if (! checkIfPlayerAgreed(player) && moduleConfig.IS_AGREEMENT_ON_FIRST_JOIN)
            openBook(player);
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
            bookPages.add(MessageUtil.getMessage(page, moduleConfig.AGREEMENT_AGREE_BUTTON,
                    moduleConfig.AGREEMENT_REJECT_BUTTON));
        }
        return Book.book(bookTitle, bookAuthor, bookPages);
    }

    private Boolean checkIfPlayerAgreed(Player player) {
        // TODO: Implement logic to check if player has agreed
        return false;
    }

    private void openBook(Player player) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.openBook(book);
        }, configManager.getModuleConfig().AGREEMENT_DELAY);

        // Schedule a task to kick the player if they do not agree within the timeout
        if (configManager.getModuleConfig().IS_AGREEMENT_KICK_ON_TIMEOUT) {
            int taskId = Bukkit.getScheduler().runTaskLater(plugin, () -> {
                kickPlayer(player);
            }, configManager.getModuleConfig().AGREEMENT_TIMEOUT).getTaskId();
            playerTaskMap.put(player.getUniqueId(), taskId);
        }
    }

    private void kickPlayer(Player player) {
        playerTaskMap.remove(player.getUniqueId());
        player.kick(MessageUtil.getMessage(configManager.getModuleConfig().AGREEMENT_KICK_MESSAGE));
    }

    public void onReject(Player player) {
        if (configManager.getModuleConfig().IS_AGREEMENT_KICK_ON_REJECT)
            kickPlayer(player);
    }

}
