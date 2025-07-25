package net.kamiland.kamihub.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.config.SpawnConfig;
import net.kamiland.kamihub.data.model.spawn.SpawnLocation;
import net.kamiland.kamihub.module.SpawnModule;
import org.bukkit.entity.Player;

import java.util.Locale;

@Command(name = "spawn")
public class SpawnCommand {

    private final KamiHub plugin;
    private final SpawnModule spawnModule;
    private final SpawnConfig spawns;
    private final MessageConfig messages;

    public SpawnCommand(KamiHub plugin) {
        this.plugin = plugin;
        this.spawnModule = (SpawnModule) plugin.getModuleManager().getModule("spawn");
        this.spawns = plugin.getConfigManager().getSpawnConfig();
        this.messages = plugin.getConfigManager().getMessageConfig();
    }

    @Execute
    @Permission("kamihub.spawn")
    public void executeSpawn(@Context Player sender) {
        spawnModule.spawn(sender);
    }

    @Execute(name = "add")
    @Permission("kamihub.spawn.add")
    public void executeSpawnAdd(@Context Player sender, @Arg("name") String name) {
        name = name.toLowerCase(Locale.ROOT);
        spawns.SPAWN_LOCATIONS.add(new SpawnLocation(name, sender.getLocation()));
        spawns.save();
        sender.sendMessage(messages.getMessage(sender, "modules.spawn.add"));
    }

    @Execute(name = "set")
    @Permission("kamihub.spawn.set")
    public void executeSpawnSet(@Context Player sender, @Arg("name") String name) {
        name = name.toLowerCase(Locale.ROOT);
        String finalName = name;
        if (spawns.SPAWN_LOCATIONS.stream().noneMatch(sl -> sl.getName().equals(finalName))) {
            sender.sendMessage(messages.getMessage(sender, "modules.spawn.not-found"));
            return;
        }
        spawns.SPAWN_LOCATIONS.removeIf(spawnLocation -> spawnLocation.getName().equals(finalName));
        spawns.SPAWN_LOCATIONS.add(new SpawnLocation(name, sender.getLocation()));
        spawns.save();
        sender.sendMessage(messages.getMessage(sender, "modules.spawn.set"));
    }

    @Execute(name = "remove")
    @Permission("kamihub.spawn.remove")
    public void executeSpawnRemove(@Context Player sender, @Arg("name") String name) {
        name = name.toLowerCase(Locale.ROOT);
        String finalName = name;
        if (spawns.SPAWN_LOCATIONS.stream().noneMatch(sl -> sl.getName().equals(finalName))) {
            sender.sendMessage(messages.getMessage(sender, "modules.spawn.not-found"));
            return;
        }
        spawns.SPAWN_LOCATIONS.removeIf(spawnLocation -> spawnLocation.getName().equals(finalName));
        spawns.save();
        sender.sendMessage(messages.getMessage(sender, "modules.spawn.remove"));
    }

    @Execute(name = "list")
    @Permission("kamihub.spawn.list")
    public void executeSpawnList(@Context Player sender) {
        sender.sendMessage(messages.getMessageWithComponentReplacements(sender, "modules.spawn.list", SpawnLocation.toComponent(spawns.SPAWN_LOCATIONS.toArray(new SpawnLocation[0]))));
    }

}
