package net.kamiland.ultimatehub.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kamiland.ultimatehub.config.MessageConfig;
import net.kamiland.ultimatehub.config.SpawnConfig;
import net.kamiland.ultimatehub.data.model.spawn.SpawnLocation;
import net.kamiland.ultimatehub.manager.ConfigManager;
import net.kamiland.ultimatehub.module.SpawnModule;
import org.bukkit.entity.Player;

import static net.kamiland.ultimatehub.config.MessageConfig.Message.*;

@Command(name = "spawn")
public class SpawnCommand {

    private final SpawnModule spawnModule;
    private final SpawnConfig spawn;
    private final MessageConfig message;

    public SpawnCommand(ConfigManager configManager, SpawnModule spawnModule) {
        this.spawnModule = spawnModule;
        this.spawn = configManager.getSpawnConfig();
        this.message = configManager.getMessageConfig();
    }

    @Execute
    @Permission("ultimatehub.spawn")
    public void executeSpawn(@Context Player sender) {
        spawnModule.spawn(sender);
    }

    @Execute
    @Permission("ultimatehub.spawn.add")
    public void executeSpawnAdd(@Context Player sender, @Arg("name") String name) {
        spawn.SPAWN_LOCATIONS.add(new SpawnLocation(name, sender.getLocation()));
        spawn.save();
        sender.sendMessage(message.getMessage(sender, SPAWN_ADD));
    }

    @Execute
    @Permission("ultimatehub.spawn.set")
    public void executeSpawnSet(@Context Player sender, @Arg("name") String name) {
        spawn.SPAWN_LOCATIONS.removeIf(sp -> sp.getName().equals(name));
        spawn.SPAWN_LOCATIONS.add(new SpawnLocation(name, sender.getLocation()));
        spawn.save();
        sender.sendMessage(message.getMessage(sender, SPAWN_SET));
    }

    @Execute
    @Permission("ultimatehub.spawn.remove")
    public void executeSpawnRemove(@Context Player sender, @Arg("name") String name) {
        spawn.SPAWN_LOCATIONS.removeIf(sp -> sp.getName().equals(name));
        spawn.save();
        sender.sendMessage(message.getMessage(sender, SPAWN_REMOVE));
    }

    @Execute
    @Permission("ultimatehub.spawn.list")
    public void executeSpawnList(@Context Player sender) {
        sender.sendMessage(message.getMessage(sender, SPAWN_LIST, spawn.SPAWN_LOCATIONS.toString()));
    }

}
