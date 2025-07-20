package net.kamiland.kamihub.data.model.spawn;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.kamiland.kamihub.util.MessageUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;

import java.util.Arrays;

@Data
@AllArgsConstructor
public class SpawnLocation {

    private final String name;
    private Location location;

    public static Component toComponent(SpawnLocation[] spawnLocations) {
        return Component.join(
                JoinConfiguration.separator(Component.text(", ").color(NamedTextColor.GRAY)),
                Arrays.stream(spawnLocations).map(SpawnLocation::toComponent).toList()
        );
    }

    public Component toComponent() {
        String hoverText = String.format(
                "<gray>world=%s\n<gray>x=%.2f\n<gray>y=%.2f\n<gray>z=%.2f\n<gray>yaw=%.2f\n<gray>pitch=%.2f",
                location.getWorld().getName(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch()
        );
        return MessageUtil.getMessage("<hover:show_text:'" + hoverText + "'><aqua>" + name + "</hover>");
    }

}
