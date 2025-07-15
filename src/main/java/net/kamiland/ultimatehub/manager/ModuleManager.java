package net.kamiland.ultimatehub.manager;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.data.impl.player.RuntimePlayerDataManager;
import net.kamiland.ultimatehub.module.*;
import net.kamiland.ultimatehub.module.Module;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;

public class ModuleManager {

    private final HashMap<String, Module> modules = new HashMap<>();

    public ModuleManager(UltimateHub plugin, ConfigManager configManager, RuntimePlayerDataManager runtimePDM) {
        put(new ActionBarModule(plugin, configManager));
        put(new AgreementModule(plugin, configManager, runtimePDM));
        put(new AntiAttackModule(plugin, configManager));
        put(new AntiBreakModule(plugin, configManager));
        put(new AntiDamageModule(plugin, configManager));
        put(new AntiDropModule(plugin, configManager));
        put(new AntiHungerModule(plugin, configManager));
        put(new AntiInteractModule(plugin, configManager));
        put(new AntiPickupModule(plugin, configManager));
        put(new AntiPlaceModule(plugin, configManager));
        put(new AntiProjectileModule(plugin, configManager));
        put(new BossBarModule(plugin, configManager));
        put(new BroadcastModule(plugin, configManager));
        put(new ClearChatModule(plugin, configManager));
        put(new JQMessageModule(plugin, configManager));
        put(new PotionEffectModule(plugin, configManager));
    }

    @Nullable
    public Module getModule(String name) {
        return modules.get(name);
    }

    public void enable(String name) {
        Objects.requireNonNull(modules.get(name), "Module does not exist").setEnabled(true);
    }

    public void disable(String name) {
        Objects.requireNonNull(modules.get(name), "Module does not exist").setEnabled(false);
    }

    public void disableAll() {
        for (Module module : modules.values()) {
            module.setEnabled(false);
        }
    }

    public boolean isModuleEnabled(String name) {
        Module module = getModule(name);
        return module != null && module.isEnabled();
    }

    private void put(Module module) {
        modules.put(module.getName(), module);
    }

}
