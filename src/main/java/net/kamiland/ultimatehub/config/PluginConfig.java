package net.kamiland.ultimatehub.config;

import net.kamiland.ultimatehub.UltimateHub;

public class PluginConfig extends Config {

    public String CONFIG_VERSION;

    public boolean IS_ACTIONBAR_ENABLED;
    public long ACTIONBAR_INTERVAL;
    public String[] ACTIONBAR_MESSAGES;

    public boolean IS_AGREEMENT_ENABLED;
    public boolean IS_AGREEMENT_ON_FIRST_JOIN;
    public boolean IS_AGREEMENT_ON_EVERY_JOIN;
    public boolean IS_AGREEMENT_ON_CHANGE;
    public int AGREEMENT_DELAY;
    public boolean IS_AGREEMENT_PREVENT_CLOSE;
    public boolean IS_AGREEMENT_KICK_ON_TIMEOUT;
    public int AGREEMENT_TIMEOUT;
    public boolean IS_AGREEMENT_KICK_ON_DISAGREE;
    public String AGREEMENT_KICK_MESSAGE;
    public String[] AGREEMENT_PAGES;
    public String AGREEMENT_AGREE_BUTTON;
    public String AGREEMENT_DISAGREE_BUTTON;

    public boolean IS_ANTIBREAK_ENABLED;
    public boolean IS_ANTIBREAK_CREATIVE_ONLY;

    public boolean IS_ANTIINTERACT_ENABLED;
    public boolean IS_ANTIINTERACT_CREATIVE_ONLY;

    public boolean IS_ANTIPLACE_ENABLED;
    public boolean IS_ANTIPLACE_CREATIVE_ONLY;

    public boolean IS_ANTIDROP_ENABLED;
    public boolean IS_ANTIDROP_CREATIVE_ONLY;

    public boolean IS_ANTIHUNGER_ENABLED;

    public boolean IS_ANTIDAMAGE_ENABLED;

    public boolean IS_ANTIPROJECTILE_ENABLED;
    public boolean IS_ANTIPROJECTILE_PLAYER;
    public boolean IS_ANTIPROJECTILE_ENTITY;

    public boolean IS_ANTIATTACK_ENABLED;
    public boolean IS_ANTIATTACK_PLAYER;
    public boolean IS_ANTIATTACK_ENTITY;

    public boolean IS_BOSSBAR_ENABLED;
    public long BOSSBAR_INTERVAL;
    public float BOSSBAR_PROGRESS;
    public String BOSSBAR_COLOR;
    public String BOSSBAR_OVERLAY;
    public String[] BOSSBAR_MESSAGES;

    public boolean IS_BROADCAST_ENABLED;
    public long BROADCAST_INTERVAL;
    public String[] BROADCAST_MESSAGES;

    public boolean IS_CLEARCHAT_ENABLED;
    public boolean IS_CLEARCHAT_ON_JOIN;

    public boolean IS_EASTEREGG_ENABLED;

    public boolean IS_INVENTORY_ENABLED;
    public boolean IS_INVENTORY_CLEAR_ON_JOIN;
    public boolean IS_INVENTORY_CLEAR_ON_QUIT;
    public boolean IS_INVENTORY_GIVE_ON_JOIN;

    public boolean IS_JQMESSAGE_ENABLED;

    public boolean IS_POTIONEFFECT_ENABLED;
    public boolean IS_POTIONEFFECT_CLEAR_ON_JOIN;
    public boolean IS_POTIONEFFECT_CLEAR_ON_QUIT;
    public boolean IS_POTIONEFFECT_GIVE_ON_JOIN;

    public boolean IS_SPAWN_ENABLED;
    public boolean IS_SPAWN_ON_JOIN;
    public boolean IS_SPAWN_ON_COMMAND;

    public boolean IS_TIMECHANGER_ENABLED;
    public long TIMECHANGER_TIME;

    public boolean IS_WEATHERCHANGER_ENABLED;
    public String WEATHERCHANGER_WEATHER;

    public boolean IS_VOIDTELEPORT_ENABLED;
    public int VOIDTELEPORT_LEVEL;

    public PluginConfig(UltimateHub plugin) {
        super(plugin, "config.yml");
    }

    @Override
    public void load() {
        super.load();
        CONFIG_VERSION = config.getString("config-version", "1.0");

        IS_ACTIONBAR_ENABLED = config.getBoolean("modules.action-bar.enabled");
        ACTIONBAR_INTERVAL = config.getLong("modules.action-bar.interval");
        ACTIONBAR_MESSAGES = config.getStringList("modules.action-bar.messages").toArray(new String[0]);

        IS_AGREEMENT_ENABLED = config.getBoolean("modules.agreement.enabled");
        IS_AGREEMENT_ON_FIRST_JOIN = config.getBoolean("modules.agreement.on-first-join");
        IS_AGREEMENT_ON_EVERY_JOIN = config.getBoolean("modules.agreement.on-every-join");
        IS_AGREEMENT_ON_CHANGE = config.getBoolean("modules.agreement.on-change");
        AGREEMENT_DELAY = config.getInt("modules.agreement.delay");
        IS_AGREEMENT_PREVENT_CLOSE = config.getBoolean("modules.agreement.prevent-close");
        IS_AGREEMENT_KICK_ON_TIMEOUT = config.getBoolean("modules.agreement.kick-on-timeout");
        AGREEMENT_TIMEOUT = config.getInt("modules.agreement.timeout");
        IS_AGREEMENT_KICK_ON_DISAGREE = config.getBoolean("modules.agreement.kick-on-disagree");
        AGREEMENT_KICK_MESSAGE = config.getString("modules.agreement.kick-message");
        AGREEMENT_PAGES = config.getStringList("modules.agreement.pages").toArray(new String[0]);
        AGREEMENT_AGREE_BUTTON = config.getString("modules.agreement.agree");
        AGREEMENT_DISAGREE_BUTTON = config.getString("modules.agreement.reject");

        IS_ANTIBREAK_ENABLED = config.getBoolean("modules.anti-break.enabled");
        IS_ANTIBREAK_CREATIVE_ONLY = config.getBoolean("modules.anti-break.break-creative-only");

        IS_ANTIINTERACT_ENABLED = config.getBoolean("modules.anti-interact.enabled");
        IS_ANTIINTERACT_CREATIVE_ONLY = config.getBoolean("modules.anti-interact.creative-only");

        IS_ANTIPLACE_ENABLED = config.getBoolean("modules.anti-place.enabled");
        IS_ANTIPLACE_CREATIVE_ONLY = config.getBoolean("modules.anti-place.creative-only");

        IS_ANTIDROP_ENABLED = config.getBoolean("modules.anti-drop.enabled");
        IS_ANTIDROP_CREATIVE_ONLY = config.getBoolean("modules.anti-drop.creative-only");

        IS_ANTIHUNGER_ENABLED = config.getBoolean("modules.anti-hunger.enabled");
        IS_ANTIDAMAGE_ENABLED = config.getBoolean("modules.anti-damage.enabled");

        IS_ANTIPROJECTILE_ENABLED = config.getBoolean("modules.anti-projectile.enabled");
        IS_ANTIPROJECTILE_PLAYER = config.getBoolean("modules.anti-projectile.anti-player");
        IS_ANTIPROJECTILE_ENTITY = config.getBoolean("modules.anti-projectile.anti-entity");

        IS_ANTIATTACK_ENABLED = config.getBoolean("modules.anti-attack.enabled");
        IS_ANTIATTACK_PLAYER = config.getBoolean("modules.anti-attack.anti-attack-player");
        IS_ANTIATTACK_ENTITY = config.getBoolean("modules.anti-attack.anti-attack-entity");

        IS_BOSSBAR_ENABLED = config.getBoolean("modules.boss-bar.enabled");
        BOSSBAR_INTERVAL = config.getLong("modules.boss-bar.interval");
        BOSSBAR_PROGRESS = (float) config.getDouble("modules.boss-bar.progress");
        BOSSBAR_COLOR = config.getString("modules.boss-bar.color");
        BOSSBAR_OVERLAY = config.getString("modules.boss-bar.overlay");
        BOSSBAR_MESSAGES = config.getStringList("modules.boss-bar.messages").toArray(new String[0]);

        IS_BROADCAST_ENABLED = config.getBoolean("modules.broadcast.enabled");
        BROADCAST_INTERVAL = config.getLong("modules.broadcast.interval");
        BROADCAST_MESSAGES = config.getStringList("modules.broadcast.messages").toArray(new String[0]);

        IS_CLEARCHAT_ENABLED = config.getBoolean("modules.clear-chat.enabled");
        IS_CLEARCHAT_ON_JOIN = config.getBoolean("modules.clear-chat.on-join");

        IS_EASTEREGG_ENABLED = config.getBoolean("modules.easter-egg.enabled");

        IS_INVENTORY_ENABLED = config.getBoolean("modules.inventory.enabled");
        IS_INVENTORY_CLEAR_ON_JOIN = config.getBoolean("modules.inventory.clear-on-join");
        IS_INVENTORY_CLEAR_ON_QUIT = config.getBoolean("modules.inventory.clear-on-quit");
        IS_INVENTORY_GIVE_ON_JOIN = config.getBoolean("modules.inventory.give-on-join");

        IS_JQMESSAGE_ENABLED = config.getBoolean("modules.join&quit-message.enabled");

        IS_POTIONEFFECT_ENABLED = config.getBoolean("modules.potion-effect.enabled");
        IS_POTIONEFFECT_CLEAR_ON_JOIN = config.getBoolean("modules.potion-effect.clear-on-join");
        IS_POTIONEFFECT_CLEAR_ON_QUIT = config.getBoolean("modules.potion-effect.clear-on-quit");
        IS_POTIONEFFECT_GIVE_ON_JOIN = config.getBoolean("modules.potion-effect.give-on-join");

        IS_SPAWN_ENABLED = config.getBoolean("modules.spawn.enabled");
        IS_SPAWN_ON_JOIN = config.getBoolean("modules.spawn.on-join");
        IS_SPAWN_ON_COMMAND = config.getBoolean("modules.spawn.on-command");

        IS_TIMECHANGER_ENABLED = config.getBoolean("modules.time-changer.enabled");
        TIMECHANGER_TIME = config.getLong("modules.time-changer.time");

        IS_WEATHERCHANGER_ENABLED = config.getBoolean("modules.weather-changer.enabled");
        WEATHERCHANGER_WEATHER = config.getString("modules.weather-changer.weather");

        IS_VOIDTELEPORT_ENABLED = config.getBoolean("modules.void-teleport.enabled");
        VOIDTELEPORT_LEVEL = config.getInt("modules.void-teleport.level");
    }

}
