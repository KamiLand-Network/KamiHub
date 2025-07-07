package net.kamiland.ultimatehub.data.model.spawn;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Location;

@Data
@AllArgsConstructor
public class SpawnLocation {

    private final String name;
    private Location location;

}
