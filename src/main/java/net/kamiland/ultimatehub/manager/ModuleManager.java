package net.kamiland.ultimatehub.manager;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.module.ActionBarModule;
import net.kamiland.ultimatehub.module.Module;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;

public class ModuleManager {

    private final HashMap<String, Module> modules = new HashMap<>();

    public ModuleManager(UltimateHub plugin, ConfigManager configManager) {
        modules.put("actionbar", new ActionBarModule(plugin, configManager));
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

}
