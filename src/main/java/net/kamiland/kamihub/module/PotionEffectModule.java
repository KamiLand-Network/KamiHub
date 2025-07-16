package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PotionEffectModule extends EventModule {

    private final KamiHub plugin;
    private final ModuleConfig config;
    private List<PotionEffect> effects;

    public PotionEffectModule(KamiHub plugin, ConfigManager configManager) {
        super(plugin, "potion-effect");
        this.plugin = plugin;
        this.config = configManager.getModuleConfig();

        setEnabled(config.IS_POTIONEFFECT_ENABLED);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (isEnabled()) {
            Player player = event.getPlayer();
            if (config.IS_POTIONEFFECT_CLEAR_ON_JOIN)
                player.getActivePotionEffects().forEach(p -> player.removePotionEffect(p.getType()));
            if (config.IS_POTIONEFFECT_GIVE_ON_JOIN)
                player.addPotionEffects(effects);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (isEnabled()) {
            Player player = event.getPlayer();
            if (config.IS_POTIONEFFECT_CLEAR_ON_QUIT)
                player.getActivePotionEffects().forEach(p -> player.removePotionEffect(p.getType()));
        }
    }

    @Override
    protected void load() {
        effects = config.POTIONEFFECT_EFFECTS;
    }

    @Override
    protected void unload() {

    }

    @Override
    @Nullable
    public String getPermission() {
        return "";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return "";
    }

}
