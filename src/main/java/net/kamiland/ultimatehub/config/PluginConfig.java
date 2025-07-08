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
    public boolean IS_INVENTORY_GIVE_ON_QUIT;

    public boolean IS_JOINMESSAGE_ENABLED;

    public boolean IS_QUITMESSAGE_ENABLED;

    public boolean IS_POTIONEFFECT_ENABLED;
    public boolean IS_POTIONEFFECT_CLEAR_ON_JOIN;
    public boolean IS_POTIONEFFECT_GIVE_ON_JOIN;

    public boolean IS_SPAWN_ENABLED;
    public boolean IS_SPAWN_ON_JOIN;
    public boolean IS_SPAWN_ON_COMMAND;

    public boolean IS_VOIDTELEPORT_ENABLED;
    public int VOIDTELEPORT_LEVEL;

    public PluginConfig(UltimateHub plugin) {
        super(plugin, "config.yml");
    }

    @Override
    public void load() {
        super.load();
        CONFIG_VERSION = config.getString("config-version", "1.0");

        IS_ACTIONBAR_ENABLED = config.getBoolean("modules.action-bar.enabled", false);
        ACTIONBAR_INTERVAL = config.getLong("modules.action-bar.interval");
        ACTIONBAR_MESSAGES = config.getStringList("modules.action-bar.messages").toArray(new String[0]);

        IS_AGREEMENT_ENABLED = config.getBoolean("modules.agreement.enabled", false);
        IS_AGREEMENT_ON_FIRST_JOIN = config.getBoolean("modules.agreement.on-first-join");
        IS_AGREEMENT_ON_EVERY_JOIN = config.getBoolean("modules.agreement.on-every-join");
        IS_AGREEMENT_ON_CHANGE = config.getBoolean("modules.agreement.on-change");
        IS_AGREEMENT_KICK_ON_DISAGREE = config.getBoolean("modules.agreement.kick-on-disagree");
        AGREEMENT_KICK_MESSAGE = config.getString("modules.agreement.kick-message");
        AGREEMENT_PAGES = config.getStringList("modules.agreement.pages").toArray(new String[0]);

        IS_BROADCAST_ENABLED = config.getBoolean("modules.broadcast.enabled", false);
        BROADCAST_INTERVAL = config.getLong("modules.broadcast.interval");
        BROADCAST_MESSAGES = config.getStringList("modules.broadcast.messages").toArray(new String[0]);

        IS_CLEARCHAT_ENABLED = config.getBoolean("modules.clear-chat.enabled", false);
        IS_CLEARCHAT_ON_JOIN = config.getBoolean("modules.clear-chat.on-join");

        IS_EASTEREGG_ENABLED = config.getBoolean("modules.easter-egg.enabled", false);

        IS_INVENTORY_ENABLED = config.getBoolean("modules.inventory.enabled", false);
        IS_INVENTORY_CLEAR_ON_JOIN = config.getBoolean("modules.inventory.clear-on-join");
        IS_INVENTORY_CLEAR_ON_QUIT = config.getBoolean("modules.inventory.clear-on-quit");
        IS_INVENTORY_GIVE_ON_JOIN = config.getBoolean("modules.inventory.give-on-join");
        IS_INVENTORY_GIVE_ON_QUIT = config.getBoolean("modules.inventory.give-on-quit");

        IS_JOINMESSAGE_ENABLED = config.getBoolean("modules.join-message.enabled", false);

        IS_QUITMESSAGE_ENABLED = config.getBoolean("modules.quit-message.enabled", false);

        IS_POTIONEFFECT_ENABLED = config.getBoolean("modules.potion-effect.enabled", false);
        IS_POTIONEFFECT_CLEAR_ON_JOIN = config.getBoolean("modules.potion-effect.clear-on-join");
        IS_POTIONEFFECT_GIVE_ON_JOIN = config.getBoolean("modules.potion-effect.give-on-join");

        IS_SPAWN_ENABLED = config.getBoolean("modules.spawn.enabled", false);
        IS_SPAWN_ON_JOIN = config.getBoolean("modules.spawn.on-join");
        IS_SPAWN_ON_COMMAND = config.getBoolean("modules.spawn.on-command");

        IS_VOIDTELEPORT_ENABLED = config.getBoolean("modules.void-teleport.enabled", false);
        VOIDTELEPORT_LEVEL = config.getInt("modules.void-teleport.level");
        AGREEMENT_AGREE_BUTTON = config.getString("modules.agreement.agree-button");
        AGREEMENT_DISAGREE_BUTTON = config.getString("modules.agreement.disagree-button");

        IS_ANTIBREAK_ENABLED = config.getBoolean("modules.anti-break.enabled", false);
        IS_ANTIBREAK_CREATIVE_ONLY = config.getBoolean("modules.anti-break.break-creative-only");

        IS_ANTIINTERACT_ENABLED = config.getBoolean("modules.anti-interact.enabled", false);
        IS_ANTIINTERACT_CREATIVE_ONLY = config.getBoolean("modules.anti-interact.interact-creative-only");

        IS_ANTIPLACE_ENABLED = config.getBoolean("modules.anti-place.enabled", false);
        IS_ANTIPLACE_CREATIVE_ONLY = config.getBoolean("modules.anti-place.place-creative-only");

        IS_BOSSBAR_ENABLED = config.getBoolean("modules.boss-bar.enabled", false);
        BOSSBAR_INTERVAL = config.getLong("modules.boss-bar.interval");
        BOSSBAR_PROGRESS = (float) config.getDouble("modules.boss-bar.progress");
        BOSSBAR_COLOR = config.getString("modules.boss-bar.color");
        BOSSBAR_OVERLAY = config.getString("modules.boss-bar.overlay");
        BOSSBAR_MESSAGES = config.getStringList("modules.boss-bar.messages").toArray(new String[0]);
    }

}
