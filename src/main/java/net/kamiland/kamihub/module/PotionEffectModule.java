package net.kamiland.kamihub.module;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ModuleConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PotionEffectModule extends EventModule {

    private final ModuleConfig config;
    private List<PotionEffect> effects;

    public PotionEffectModule(KamiHub plugin) {
        super(plugin, "potion-effect");
        this.config = plugin.getConfigManager().getModuleConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (isEnabled()) {
            Player player = event.getPlayer();
            if (config.IS_POTIONEFFECT_CLEAR_ON_JOIN)
                player.getActivePotionEffects().forEach(p -> player.removePotionEffect(p.getType()));
            if (config.IS_POTIONEFFECT_GIVE_ON_JOIN && player.hasPermission(getPermission()))
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
    @NotNull
    public String getPermission() {
        return "kamihub.potion-effect";
    }

    @Override
    @Nullable
    public String getBypassPermission() {
        return null;
    }

}
