package net.kamiland.kamihub.manager;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.module.*;
import net.kamiland.kamihub.module.Module;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;

public class ModuleManager {

    private final HashMap<String, Module> modules = new HashMap<>();

    public ModuleManager(KamiHub plugin) {
        put(new ActionBarModule(plugin));
        put(new AgreementModule(plugin));
        put(new AntiAttackModule(plugin));
        put(new AntiBreakModule(plugin));
        put(new AntiDamageModule(plugin));
        put(new AntiDropModule(plugin));
        put(new AntiHungerModule(plugin));
        put(new AntiInteractModule(plugin));
        put(new AntiPickupModule(plugin));
        put(new AntiPlaceModule(plugin));
        put(new AntiProjectileModule(plugin));
        put(new BossBarModule(plugin));
        put(new BroadcastModule(plugin));
        put(new ClearChatModule(plugin));
        put(new JQMessageModule(plugin));
        put(new PotionEffectModule(plugin));
        put(new SpawnModule(plugin));
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
