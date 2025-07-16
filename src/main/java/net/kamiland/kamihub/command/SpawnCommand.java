package net.kamiland.kamihub.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kamiland.kamihub.KamiHub;
import net.kamiland.kamihub.config.MessageConfig;
import net.kamiland.kamihub.config.SpawnConfig;
import net.kamiland.kamihub.module.SpawnModule;
import org.bukkit.entity.Player;

@Command(name = "spawn")
public class SpawnCommand {

    private final SpawnModule spawnModule;
    private final SpawnConfig spawns;
    private final MessageConfig messages;

    public SpawnCommand(KamiHub plugin) {
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

    }

    @Execute(name = "set")
    @Permission("kamihub.spawn.set")
    public void executeSpawnSet(@Context Player sender, @Arg("name") String name) {

    }

    @Execute(name = "remove")
    @Permission("kamihub.spawn.remove")
    public void executeSpawnRemove(@Context Player sender, @Arg("name") String name) {

    }

    @Execute(name = "list")
    @Permission("kamihub.spawn.list")
    public void executeSpawnList(@Context Player sender) {

    }

}
