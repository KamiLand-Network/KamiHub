package net.kamiland.kamihub.manager;

import lombok.Getter;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.module.*;
import net.kamiland.kamihub.module.Module;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ModuleManager {

    private final KamiHub plugin;
    @Getter
    private final LinkedHashMap<String, Module> modules = new LinkedHashMap<>();

    public ModuleManager(KamiHub plugin) {
        this.plugin = plugin;

        put(new ActionBarModule(plugin));
        put(new AgreementModule(plugin));
        put(new AntiAttackModule(plugin));
        put(new AntiBreakModule(plugin));
        put(new AntiDamageModule(plugin));
        put(new AntiDropModule(plugin));
        put(new AntiHungerModule(plugin));
        put(new AntiUseModule(plugin));
        put(new AntiPickupModule(plugin));
        put(new AntiPlaceModule(plugin));
        put(new AntiProjectileModule(plugin));
        put(new BossBarModule(plugin));
        put(new BroadcastModule(plugin));
        put(new ClearChatModule(plugin));
        put(new FlyModule(plugin));
        put(new InventoryModule(plugin));
        put(new JoinQuitMessageModule(plugin));
        put(new PotionEffectModule(plugin));
        put(new SpawnModule(plugin));
        put(new VoidTeleportModule(plugin));
    }

    public void load() {
        enableByConfig();
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

    public void enableByConfig() {
        ConfigurationSection section = plugin.getConfigManager().getModuleConfig().getConfig();
        section = Objects.requireNonNull(section).getConfigurationSection("modules");
        Objects.requireNonNull(section);
        for (String name : modules.keySet()) {
            if (section.getBoolean(name + ".enabled")) {
                enable(name);
            }
        }
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

    private void put(Module module) {
        modules.put(module.getName(), module);
    }

}
