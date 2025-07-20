package net.kamiland.kamihub.action;

import net.kamiland.kamihub.KamiHub;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class ActionResolver {

    private final KamiHub plugin;
    private final Map<String, Action> prefixActionMap = new LinkedHashMap<>();

    public ActionResolver(KamiHub plugin) {
        this.plugin = plugin;
        put(new BroadcastAction(plugin));
        put(new ConsoleCommandAction(plugin));
        put(new PlayerCommandAction(plugin));
        put(new PlayerMessageAction(plugin));
    }

    @Nullable
    public Action getAction(String action) {
        for (Map.Entry<String, Action> entry : prefixActionMap.entrySet()) {
            if (action.startsWith(entry.getKey()))
                return entry.getValue();
        }
        return null;
    }

    public void resolve(@Nullable Player player, String action) {
        Action act = getAction(action);
        if (act == null) {
            plugin.getSLF4JLogger().warn("Unknown action: {}", action);
            return;
        }
        act.resolve(player, action.replaceFirst(act.getPrefixRegex(), ""));
    }

    public void registerAction(Action action) {
        put(action);
    }

    public void unregisterAction(Action action) {
        prefixActionMap.remove(action.getPrefix());
    }

    private void put(Action action) {
        prefixActionMap.put(action.getPrefix(), action);
    }

}
