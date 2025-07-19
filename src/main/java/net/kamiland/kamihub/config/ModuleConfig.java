package net.kamiland.kamihub.config;

import net.kamiland.kamihub.KamiHub;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class ModuleConfig extends Config {

    public boolean IS_ACTIONBAR_ENABLED;
    public long ACTIONBAR_INTERVAL;
    public Map<String, List<String>> ACTIONBAR_WORLDS;

    public boolean IS_AGREEMENT_ENABLED;
    public boolean IS_AGREEMENT_ON_JOIN;
    public boolean IS_AGREEMENT_ON_EVERY_JOIN;
    public boolean IS_AGREEMENT_ON_CHANGE;
    public int AGREEMENT_DELAY;
    public boolean IS_AGREEMENT_KICK_ON_TIMEOUT;
    public int AGREEMENT_TIMEOUT;
    public boolean IS_AGREEMENT_KICK_ON_REJECT;
    public String AGREEMENT_KICK_MESSAGE;
    public String[] AGREEMENT_PAGES;
    public String AGREEMENT_ACCEPT_BUTTON;
    public String AGREEMENT_REJECT_BUTTON;
    public String[] AGREEMENT_ACCEPT_COMMANDS;
    public String[] AGREEMENT_REJECT_COMMANDS;

    public boolean IS_ANTIBREAK_ENABLED;
    public boolean IS_ANTIBREAK_CREATIVE_ONLY;
    public List<String> ANTIBREAK_WORLDS;

    public boolean IS_ANTIUSE_ENABLED;
    public boolean IS_ANTIUSE_CREATIVE_ONLY;
    public List<String> ANTIUSE_WORLDS;

    public boolean IS_ANTIPLACE_ENABLED;
    public boolean IS_ANTIPLACE_CREATIVE_ONLY;
    public List<String> ANTIPLACE_WORLDS;

    public boolean IS_ANTIDROP_ENABLED;
    public boolean IS_ANTIDROP_CREATIVE_ONLY;
    public List<String> ANTIDROP_WORLDS;

    public boolean IS_ANTIPICKUP_ENABLED;
    public List<String> ANTIPICKUP_WORLDS;

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
    public boolean IS_INVENTORY_PREVENT_MOVING;

    public boolean IS_JQMESSAGE_ENABLED;
    public Map<String, Map<String, List<String>>> JQMESSAGE_GROUPS;
    public Map<String, Integer> JQMESSAGE_GROUPS_PRIORITY;

    public boolean IS_POTIONEFFECT_ENABLED;
    public boolean IS_POTIONEFFECT_CLEAR_ON_JOIN;
    public boolean IS_POTIONEFFECT_CLEAR_ON_QUIT;
    public boolean IS_POTIONEFFECT_GIVE_ON_JOIN;
    public List<PotionEffect> POTIONEFFECT_EFFECTS;

    public boolean IS_SPAWN_ENABLED;
    public boolean IS_SPAWN_ON_JOIN;

    public boolean IS_VOIDTELEPORT_ENABLED;
    public int VOIDTELEPORT_LEVEL;
    public List<String> VOIDTELEPORT_WORLDS;

    public ModuleConfig(KamiHub plugin) {
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
        IS_AGREEMENT_ON_JOIN = config.getBoolean("modules.agreement.on-join");
        IS_AGREEMENT_ON_EVERY_JOIN = config.getBoolean("modules.agreement.on-every-join");
        IS_AGREEMENT_ON_CHANGE = config.getBoolean("modules.agreement.on-change");
        AGREEMENT_DELAY = config.getInt("modules.agreement.delay");
        IS_AGREEMENT_KICK_ON_TIMEOUT = config.getBoolean("modules.agreement.kick-on-timeout");
        AGREEMENT_TIMEOUT = config.getInt("modules.agreement.timeout");
        IS_AGREEMENT_KICK_ON_REJECT = config.getBoolean("modules.agreement.kick-on-reject");
        AGREEMENT_KICK_MESSAGE = config.getString("modules.agreement.kick-message");
        AGREEMENT_PAGES = config.getStringList("modules.agreement.pages").toArray(new String[0]);
        AGREEMENT_ACCEPT_BUTTON = config.getString("modules.agreement.accept");
        AGREEMENT_REJECT_BUTTON = config.getString("modules.agreement.reject");
        AGREEMENT_ACCEPT_COMMANDS = config.getStringList("modules.agreement.accept-commands").toArray(new String[0]);
        AGREEMENT_REJECT_COMMANDS = config.getStringList("modules.agreement.reject-commands").toArray(new String[0]);

        IS_ANTIBREAK_ENABLED = config.getBoolean("modules.anti-break.enabled");
        IS_ANTIBREAK_CREATIVE_ONLY = config.getBoolean("modules.anti-break.break-creative-only");
        ANTIBREAK_WORLDS = config.getStringList("modules.anti-break.worlds");

        IS_ANTIUSE_ENABLED = config.getBoolean("modules.anti-use.enabled");
        IS_ANTIUSE_CREATIVE_ONLY = config.getBoolean("modules.anti-use.creative-only");
        ANTIUSE_WORLDS = config.getStringList("modules.anti-use.worlds");

        IS_ANTIPLACE_ENABLED = config.getBoolean("modules.anti-place.enabled");
        IS_ANTIPLACE_CREATIVE_ONLY = config.getBoolean("modules.anti-place.creative-only");
        ANTIPLACE_WORLDS = config.getStringList("modules.anti-place.worlds");

        IS_ANTIDROP_ENABLED = config.getBoolean("modules.anti-drop.enabled");
        IS_ANTIDROP_CREATIVE_ONLY = config.getBoolean("modules.anti-drop.creative-only");
        ANTIDROP_WORLDS = config.getStringList("modules.anti-drop.worlds");

        IS_ANTIPICKUP_ENABLED = config.getBoolean("modules.anti-pickup.enabled");
        ANTIPICKUP_WORLDS = config.getStringList("modules.anti-pickup.worlds");

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
        IS_INVENTORY_PREVENT_MOVING = config.getBoolean("modules.inventory.prevent-moving");

        IS_JQMESSAGE_ENABLED = config.getBoolean("modules.join-quit-message.enabled");
        section = Objects.requireNonNull(config.getConfigurationSection("modules.join-quit-message.groups"));
        JQMESSAGE_GROUPS = new HashMap<>();
        JQMESSAGE_GROUPS_PRIORITY = new HashMap<>();
        for (String group : section.getKeys(false)) {
            Map<String, List<String>> groupData = new HashMap<>();
            groupData.put("join", config.getStringList("modules.join-quit-message.groups." + group + ".join"));
            groupData.put("quit", config.getStringList("modules.join-quit-message.groups." + group + ".quit"));
            JQMESSAGE_GROUPS.put(group, groupData);
            JQMESSAGE_GROUPS_PRIORITY.put(group, config.getInt("modules.join-quit-message.groups." + group + ".priority"));
        }

        IS_POTIONEFFECT_ENABLED = config.getBoolean("modules.potion-effect.enabled");
        IS_POTIONEFFECT_CLEAR_ON_JOIN = config.getBoolean("modules.potion-effect.clear-on-join");
        IS_POTIONEFFECT_CLEAR_ON_QUIT = config.getBoolean("modules.potion-effect.clear-on-quit");
        IS_POTIONEFFECT_GIVE_ON_JOIN = config.getBoolean("modules.potion-effect.give-on-join");
        POTIONEFFECT_EFFECTS = new ArrayList<>();
        for (Map<?, ?> effectMap : config.getMapList("modules.potion-effect.effects")) {
            PotionEffectType type = Registry.POTION_EFFECT_TYPE.get(Objects.requireNonNull(NamespacedKey.fromString((String) effectMap.get("type")), "Unknown potion effect type!"));
            if (type == null) continue;
            int duration = effectMap.get("duration").equals("infinite")
                    ? -1
                    : Integer.parseInt((String) effectMap.get("duration")) * 20;
            PotionEffect effect = new PotionEffect(
                    type,
                    duration,
                    (Integer) effectMap.get("amplifier"),
                    false,
                    (Boolean) effectMap.get("particles"),
                    true
            );
            POTIONEFFECT_EFFECTS.add(effect);
        }

        IS_SPAWN_ENABLED = config.getBoolean("modules.spawn.enabled");
        IS_SPAWN_ON_JOIN = config.getBoolean("modules.spawn.on-join");

        IS_VOIDTELEPORT_ENABLED = config.getBoolean("modules.void-teleport.enabled");
        VOIDTELEPORT_LEVEL = config.getInt("modules.void-teleport.level");
        VOIDTELEPORT_WORLDS = config.getStringList("modules.void-teleport.worlds");
    }

}
