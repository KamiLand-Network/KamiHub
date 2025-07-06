package net.kamiland.ultimatehub.config;

import net.kamiland.ultimatehub.data.model.spawn.SpawnLocation;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;

public interface SpawnConfig extends Config {

    @Nullable
    LinkedList<SpawnLocation> getSpawnLocations();

}
