package net.kamiland.ultimatehub.config;

import net.kamiland.ultimatehub.UltimateHub;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class ModuleConfig extends Config {

    public boolean IS_ACTIONBAR_ENABLED;
    public long ACTIONBAR_INTERVAL;
    public Map<String, List<String>> ACTIONBAR_WORLDS;

    public boolean IS_AGREEMENT_ENABLED;
    public boolean IS_AGREEMENT_ON_FIRST_JOIN;
    public boolean IS_AGREEMENT_ON_EVERY_JOIN;
    public boolean IS_AGREEMENT_ON_CHANGE;
    public int AGREEMENT_DELAY;
    public boolean IS_AGREEMENT_PREVENT_CLOSE;
    public boolean IS_AGREEMENT_KICK_ON_TIMEOUT;
    public int AGREEMENT_TIMEOUT;
    public boolean IS_AGREEMENT_KICK_ON_REJECT;
    public String AGREEMENT_KICK_MESSAGE;
    public String[] AGREEMENT_PAGES;
    public String AGREEMENT_AGREE_BUTTON;
    public String AGREEMENT_REJECT_BUTTON;

    public boolean IS_ANTIBREAK_ENABLED;
    public boolean IS_ANTIBREAK_CREATIVE_ONLY;
    public List<String> ANTIBREAK_WORLDS;

    public boolean IS_ANTIINTERACT_ENABLED;
    public boolean IS_ANTIINTERACT_CREATIVE_ONLY;
    public List<String> ANTIINTERACT_WORLDS;

    public boolean IS_ANTIPLACE_ENABLED;
    public boolean IS_ANTIPLACE_CREATIVE_ONLY;
    public List<String> ANTIPLACE_WORLDS;

    public boolean IS_ANTIDROP_ENABLED;
    public boolean IS_ANTIDROP_CREATIVE_ONLY;
    public List<String> ANTIDROP_WORLDS;

    public boolean IS_ANTIHUNGER_ENABLED;
    public List<String> ANTIHUNGER_WORLDS;

    public boolean IS_ANTIDAMAGE_ENABLED;
    public List<String> ANTIDAMAGE_WORLDS;

    public boolean IS_ANTIPROJECTILE_ENABLED;
    public boolean IS_ANTIPROJECTILE_PLAYER;
    public boolean IS_ANTIPROJECTILE_ENTITY;
    public List<String> ANTIPROJECTILE_WORLDS;

    public boolean IS_ANTIATTACK_ENABLED;
    public boolean IS_ANTIATTACK_PLAYER;
    public boolean IS_ANTIATTACK_ENTITY;
    public List<String> ANTIATTACK_WORLDS;

    public boolean IS_BOSSBAR_ENABLED;
    public long BOSSBAR_INTERVAL;
    public Map<String, List<String>> BOSSBAR_WORLDS;

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
    public Map<String, Map<String, List<String>>> JQMESSAGE_GROUPS;

    public boolean IS_POTIONEFFECT_ENABLED;
    public boolean IS_POTIONEFFECT_CLEAR_ON_JOIN;
    public boolean IS_POTIONEFFECT_CLEAR_ON_QUIT;
    public boolean IS_POTIONEFFECT_GIVE_ON_JOIN;
    public List<PotionEffect> POTIONEFFECT_EFFECTS;

    public boolean IS_SPAWN_ENABLED;
    public boolean IS_SPAWN_ON_JOIN;
    public boolean IS_SPAWN_ON_COMMAND;

    public boolean IS_TIMECHANGER_ENABLED;
    public Map<String, Integer> TIMECHANGER_WORLDS;

    public boolean IS_WEATHERCHANGER_ENABLED;
    public Map<String, String> WEATHERCHANGER_WORLDS;

    public boolean IS_VOIDTELEPORT_ENABLED;
    public int VOIDTELEPORT_LEVEL;
    public List<String> VOIDTELEPORT_WORLDS;

    public ModuleConfig(UltimateHub plugin) {
        super(plugin, "modules.yml");
    }

    @Override
    public void load() {
        super.load();
        ConfigurationSection section;

        IS_ACTIONBAR_ENABLED = config.getBoolean("modules.action-bar.enabled");
        ACTIONBAR_INTERVAL = config.getLong("modules.action-bar.interval");
        section = Objects.requireNonNull(config.getConfigurationSection("modules.action-bar.worlds"));
        ACTIONBAR_WORLDS = new HashMap<>();
        for (String world : section.getKeys(false)) {
            ACTIONBAR_WORLDS.put(world, config.getStringList("modules.action-bar.worlds." + world));
        }

        IS_AGREEMENT_ENABLED = config.getBoolean("modules.agreement.enabled");
        IS_AGREEMENT_ON_FIRST_JOIN = config.getBoolean("modules.agreement.on-first-join");
        IS_AGREEMENT_ON_EVERY_JOIN = config.getBoolean("modules.agreement.on-every-join");
        IS_AGREEMENT_ON_CHANGE = config.getBoolean("modules.agreement.on-change");
        AGREEMENT_DELAY = config.getInt("modules.agreement.delay");
        IS_AGREEMENT_PREVENT_CLOSE = config.getBoolean("modules.agreement.prevent-close");
        IS_AGREEMENT_KICK_ON_TIMEOUT = config.getBoolean("modules.agreement.kick-on-timeout");
        AGREEMENT_TIMEOUT = config.getInt("modules.agreement.timeout");
        IS_AGREEMENT_KICK_ON_REJECT = config.getBoolean("modules.agreement.kick-on-reject");
        AGREEMENT_KICK_MESSAGE = config.getString("modules.agreement.kick-message");
        AGREEMENT_PAGES = config.getStringList("modules.agreement.pages").toArray(new String[0]);
        AGREEMENT_AGREE_BUTTON = config.getString("modules.agreement.agree");
        AGREEMENT_REJECT_BUTTON = config.getString("modules.agreement.reject");

        IS_ANTIBREAK_ENABLED = config.getBoolean("modules.anti-break.enabled");
        IS_ANTIBREAK_CREATIVE_ONLY = config.getBoolean("modules.anti-break.break-creative-only");
        ANTIBREAK_WORLDS = config.getStringList("modules.anti-break.worlds");

        IS_ANTIINTERACT_ENABLED = config.getBoolean("modules.anti-interact.enabled");
        IS_ANTIINTERACT_CREATIVE_ONLY = config.getBoolean("modules.anti-interact.creative-only");
        ANTIINTERACT_WORLDS = config.getStringList("modules.anti-interact.worlds");

        IS_ANTIPLACE_ENABLED = config.getBoolean("modules.anti-place.enabled");
        IS_ANTIPLACE_CREATIVE_ONLY = config.getBoolean("modules.anti-place.creative-only");
        ANTIPLACE_WORLDS = config.getStringList("modules.anti-place.worlds");

        IS_ANTIDROP_ENABLED = config.getBoolean("modules.anti-drop.enabled");
        IS_ANTIDROP_CREATIVE_ONLY = config.getBoolean("modules.anti-drop.creative-only");
        ANTIDROP_WORLDS = config.getStringList("modules.anti-drop.worlds");

        IS_ANTIHUNGER_ENABLED = config.getBoolean("modules.anti-hunger.enabled");
        ANTIHUNGER_WORLDS = config.getStringList("modules.anti-hunger.worlds");
        
        IS_ANTIDAMAGE_ENABLED = config.getBoolean("modules.anti-damage.enabled");
        ANTIDAMAGE_WORLDS = config.getStringList("modules.anti-damage.worlds");

        IS_ANTIPROJECTILE_ENABLED = config.getBoolean("modules.anti-projectile.enabled");
        IS_ANTIPROJECTILE_PLAYER = config.getBoolean("modules.anti-projectile.anti-player");
        IS_ANTIPROJECTILE_ENTITY = config.getBoolean("modules.anti-projectile.anti-entity");
        ANTIPROJECTILE_WORLDS = config.getStringList("modules.anti-projectile.worlds");

        IS_ANTIATTACK_ENABLED = config.getBoolean("modules.anti-attack.enabled");
        IS_ANTIATTACK_PLAYER = config.getBoolean("modules.anti-attack.anti-attack-player");
        IS_ANTIATTACK_ENTITY = config.getBoolean("modules.anti-attack.anti-attack-entity");
        ANTIATTACK_WORLDS = config.getStringList("modules.anti-attack.worlds");

        IS_BOSSBAR_ENABLED = config.getBoolean("modules.boss-bar.enabled");
        BOSSBAR_INTERVAL = config.getLong("modules.boss-bar.interval");
        section = Objects.requireNonNull(config.getConfigurationSection("modules.boss-bar.worlds"));
        BOSSBAR_WORLDS = new HashMap<>();
        for (String world : section.getKeys(false)) {
            BOSSBAR_WORLDS.put(world, config.getStringList("modules.boss-bar.worlds." + world));
        }

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

        IS_JQMESSAGE_ENABLED = config.getBoolean("modules.join-quit-message.enabled");
        section = Objects.requireNonNull(config.getConfigurationSection("modules.join-quit-message.groups"));
        JQMESSAGE_GROUPS = new HashMap<>();
        for (String group : section.getKeys(false)) {
            Map<String, List<String>> groupData = new HashMap<>();
            groupData.put("join", config.getStringList("modules.join-quit-message.groups." + group + ".join"));
            groupData.put("quit", config.getStringList("modules.join-quit-message.groups." + group + ".quit"));
            JQMESSAGE_GROUPS.put(group, groupData);
        }

        IS_POTIONEFFECT_ENABLED = config.getBoolean("modules.potion-effect.enabled");
        IS_POTIONEFFECT_CLEAR_ON_JOIN = config.getBoolean("modules.potion-effect.clear-on-join");
        IS_POTIONEFFECT_CLEAR_ON_QUIT = config.getBoolean("modules.potion-effect.clear-on-quit");
        IS_POTIONEFFECT_GIVE_ON_JOIN = config.getBoolean("modules.potion-effect.give-on-join");
        POTIONEFFECT_EFFECTS = new ArrayList<>();
        for (Map<?, ?> effectMap : config.getMapList("modules.potion-effect.effects")) {
            PotionEffectType type = PotionEffectType.getByName(((String) effectMap.get("name")).toUpperCase());
            if (type == null) continue;
            int duration = effectMap.get("duration").equals("infinite")
                    ? Integer.MAX_VALUE
                    : Integer.parseInt((String) effectMap.get("duration")) * 20;
            PotionEffect effect = new PotionEffect(
                    type,
                    duration,
                    (Integer) effectMap.get("amplifier"),
                    false,
                    (Boolean) effectMap.get("particles")
            );
            POTIONEFFECT_EFFECTS.add(effect);
        }

        IS_SPAWN_ENABLED = config.getBoolean("modules.spawn.enabled");
        IS_SPAWN_ON_JOIN = config.getBoolean("modules.spawn.on-join");
        IS_SPAWN_ON_COMMAND = config.getBoolean("modules.spawn.on-command");

        IS_TIMECHANGER_ENABLED = config.getBoolean("modules.time-changer.enabled");
        section = Objects.requireNonNull(config.getConfigurationSection("modules.time-changer.worlds"));
        TIMECHANGER_WORLDS = new HashMap<>();
        for (String world : section.getKeys(false)) {
            TIMECHANGER_WORLDS.put(world, config.getInt("modules.time-changer.worlds." + world));
        }

        IS_WEATHERCHANGER_ENABLED = config.getBoolean("modules.weather-changer.enabled");
        section = Objects.requireNonNull(config.getConfigurationSection("modules.weather-changer.worlds"));
        WEATHERCHANGER_WORLDS = new HashMap<>();
        for (String world : section.getKeys(false)) {
            WEATHERCHANGER_WORLDS.put(world, config.getString("modules.weather-changer.worlds." + world));
        }

        IS_VOIDTELEPORT_ENABLED = config.getBoolean("modules.void-teleport.enabled");
        VOIDTELEPORT_LEVEL = config.getInt("modules.void-teleport.level");
        VOIDTELEPORT_WORLDS = config.getStringList("modules.void-teleport.worlds");
    }

}
