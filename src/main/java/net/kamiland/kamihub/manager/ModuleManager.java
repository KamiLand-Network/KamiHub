package net.kamiland.kamihub.manager;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.ModuleConfig;
import net.kamiland.kamihub.module.*;
import net.kamiland.kamihub.module.Module;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModuleManager {

    private final LinkedHashMap<String, Module> modules = new LinkedHashMap<>();
    private final HashMap<Module, Boolean> moduleEnabled = new HashMap<>();

    public ModuleManager(KamiHub plugin) {
        ModuleConfig config = plugin.getConfigManager().getModuleConfig();
        put(new ActionBarModule(plugin), config.IS_ACTIONBAR_ENABLED);
        put(new AgreementModule(plugin), config.IS_AGREEMENT_ENABLED);
        put(new AntiAttackModule(plugin), config.IS_ANTIATTACK_ENABLED);
        put(new AntiBreakModule(plugin), config.IS_ANTIBREAK_ENABLED);
        put(new AntiDamageModule(plugin), config.IS_ANTIDAMAGE_ENABLED);
        put(new AntiDropModule(plugin), config.IS_ANTIDROP_ENABLED);
        put(new AntiHungerModule(plugin), config.IS_ANTIHUNGER_ENABLED);
        put(new AntiUseModule(plugin), config.IS_ANTIUSE_ENABLED);
        put(new AntiPickupModule(plugin), config.IS_ANTIPICKUP_ENABLED);
        put(new AntiPlaceModule(plugin), config.IS_ANTIPLACE_ENABLED);
        put(new AntiProjectileModule(plugin), config.IS_ANTIPROJECTILE_ENABLED);
        put(new BossBarModule(plugin), config.IS_BOSSBAR_ENABLED);
        put(new BroadcastModule(plugin), config.IS_BROADCAST_ENABLED);
        put(new ClearChatModule(plugin), config.IS_CLEARCHAT_ENABLED);
        put(new FlyModule(plugin), config.IS_FLY_ENABLED);
        put(new InventoryModule(plugin), config.IS_INVENTORY_ENABLED);
        put(new JQMessageModule(plugin), config.IS_JQMESSAGE_ENABLED);
        put(new PotionEffectModule(plugin), config.IS_POTIONEFFECT_ENABLED);
        put(new SpawnModule(plugin), config.IS_SPAWN_ENABLED);
        put(new VoidTeleportModule(plugin), config.IS_VOIDTELEPORT_ENABLED);
    }

    public void load() {
        moduleEnabled.forEach((module, enabled) -> {
            if (enabled) module.setEnabled(true);
        });
    }

    @Nullable
    public Module getModule(String name) {
        return modules.get(name);
    }

    public void enable(String name) {
        Map.Entry<String, Module> entry = modules.entrySet().stream()
                .filter(e -> e.getKey().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Module \"" + name + "\" does not exist"));

        entry.getValue().setEnabled(true);
    }

    public void disable(String name) {
        Map.Entry<String, Module> entry = modules.entrySet().stream()
                .filter(e -> e.getKey().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Module \"" + name + "\" does not exist"));

        entry.getValue().setEnabled(false);
    }

    public void disableAll() {
        for (Module module : modules.values()) {
            module.setEnabled(false);
        }
    }

    public Component getModulesComponent() {
        return Component.join(
                JoinConfiguration.separator(Component.text(", ").color(NamedTextColor.GRAY)),
                modules.values().stream().map(Module::toComponent).toList()
        );
    }

    public boolean isModuleEnabled(String name) {
        Module module = getModule(name);
        return module != null && module.isEnabled();
    }

    public boolean isModuleExist(String name) {
        return modules.keySet().stream().anyMatch(name::equalsIgnoreCase);
    }

    private void put(Module module, boolean enabled) {
        modules.put(module.getName(), module);
        moduleEnabled.put(module, enabled);
    }

}
