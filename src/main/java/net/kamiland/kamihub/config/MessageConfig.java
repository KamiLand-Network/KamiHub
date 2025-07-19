package net.kamiland.kamihub.config;

import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.util.MessageUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MessageConfig extends Config {

    private final String[] keys;
    private final Map<String, String> keyMsgMap = new HashMap<>();
    private final List<String> cmdHelp = new ArrayList<>();
    public boolean prefixEnabled;
    public Component prefix;

    public MessageConfig(KamiHub plugin) {
        super(plugin, "messages.yml");
        keys = new String[]{
                "general.no-permission",
                "general.reload-success",
                "general.invalid-usage",
                "general.no-console",
                "general.unknown-command-help",

                "modules.not-found",
                "modules.list",

                "modules.agreement.accept",
                "modules.agreement.reject",
                "modules.agreement.change",

                "modules.anti-attack",
                "modules.anti-break",
                "modules.anti-drop",
                "modules.anti-use",
                "modules.anti-place",
                "modules.anti-projectile",

                "modules.spawn.not-found",
                "modules.spawn.teleport",
                "modules.spawn.add",
                "modules.spawn.set",
                "modules.spawn.remove",
                "modules.spawn.list",

                "modules.void-tp"
        };
    }

    @Override
    public void load() {
        super.load();
        prefixEnabled = config.getBoolean("prefix.enabled");
        prefix = MessageUtil.getMessage(config.getString("prefix.text", "<aqua>[KamiHub]</aqua> "));

        cmdHelp.addAll(config.getStringList("command-help"));

        Arrays.stream(keys).forEach(key -> keyMsgMap.put(key, config.getString(key)));
    }

    @Nullable
    public Component getCommandHelpMessage(int index) {
        return getCommandHelpMessage(null, index);
    }

    @Nullable
    public Component getCommandHelpMessage(Player player, int index) {
        if (0 < index && index <= cmdHelp.size())
            return MessageUtil.getMessage(player, cmdHelp.get(index - 1));
        else
            return null;
    }

    public Component getMessage(@NotNull String key, String... replacements) {
        return getMessage(null, key, replacements);
    }

    public Component getMessage(@Nullable Player player, @NotNull String key, String... replacements) {
        String message = keyMsgMap.get(key);
        if (message == null) {
            plugin.getSLF4JLogger().warn("Missing message key: {}", key);
            message = "Undefined";
        }
        if (prefixEnabled) return prefix.append(MessageUtil.getMessage(player, message, replacements));
        else return MessageUtil.getMessage(player, message, replacements);
    }

    public Component getMessageWithComponentReplacements(@Nullable Player player, @NotNull String key, Component... replacements) {
        return getMessage(player, key).replaceText(builder -> {
            for (int i = 0; i < replacements.length; i ++) {
                builder.matchLiteral("{" + i + "}").replacement(replacements[i]);
            }
        });
    }

}
