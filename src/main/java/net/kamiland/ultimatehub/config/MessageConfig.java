package net.kamiland.ultimatehub.config;

import net.kamiland.ultimatehub.UltimateHub;
import net.kamiland.ultimatehub.util.MessageUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class MessageConfig extends Config {

    public boolean IS_PREFIX_ENABLED;
    public Component PREFIX;
    private String noPermission;
    private String reloadSuccess;
    private String unknownCommand;
    private String noConsole;
    private String antiAttack;
    private String antiBreak;
    private String antiDrop;
    private String antiInteract;
    private String antiPlace;
    private String antiProjectile;
    private String spawnTeleport;
    private String voidTp;

    public MessageConfig(UltimateHub plugin) {
        super(plugin, "messages.yml");
    }

    @Override
    public void load() {
        super.load();

        IS_PREFIX_ENABLED = config.getBoolean("prefix.enabled");
        PREFIX = MessageUtil.getMessage(config.getString("prefix.text"));
        noPermission = config.getString("general.no-permission");
        reloadSuccess = config.getString("general.reload-success");
        unknownCommand = config.getString("general.unknown-command");
        noConsole = config.getString("general.no-console");

        // 加载模块消息
        antiAttack = config.getString("modules.anti-attack");
        antiBreak = config.getString("modules.anti-break");
        antiDrop = config.getString("modules.anti-drop");
        antiInteract = config.getString("modules.anti-interact");
        antiPlace = config.getString("modules.anti-place");
        antiProjectile = config.getString("modules.anti-projectile");
        spawnTeleport = config.getString("modules.spawn-teleport");
        voidTp = config.getString("modules.void-tp");
    }

    public Component getMessage(Message type) {
        return getMessage(null, type);
    }

    public Component getMessage(Player player, Message type, String... replacements) {
        String msg = switch (type) {
            case NO_PERMISSION -> noPermission;
            case RELOAD_SUCCESS -> reloadSuccess;
            case UNKNOWN_COMMAND -> unknownCommand;
            case NO_CONSOLE -> noConsole;
            case ANTI_ATTACK -> antiAttack;
            case ANTI_BREAK -> antiBreak;
            case ANTI_DROP -> antiDrop;
            case ANTI_INTERACT -> antiInteract;
            case ANTI_PLACE -> antiPlace;
            case ANTI_PROJECTILE -> antiProjectile;
            case SPAWN_TELEPORT -> spawnTeleport;
            case VOID_TP -> voidTp;
        };
        return MessageUtil.getMessage(player, msg, replacements);
    }

    public enum Message {
        NO_PERMISSION,
        RELOAD_SUCCESS,
        UNKNOWN_COMMAND,
        NO_CONSOLE,
        ANTI_ATTACK,
        ANTI_BREAK,
        ANTI_DROP,
        ANTI_INTERACT,
        ANTI_PLACE,
        ANTI_PROJECTILE,
        SPAWN_TELEPORT,
        VOID_TP
    }

}
