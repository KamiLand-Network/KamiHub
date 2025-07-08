package net.kamiland.ultimatehub.util;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kamiland.ultimatehub.manager.ServerManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.bukkit.entity.Player;

/**
 * Utility class for handling message formatting and placeholder processing.
 * Provides MiniMessage integration with PlaceholderAPI support.
 *
 * @author while1cry
 */
public class MessageUtil {

    /**
     * Pre-configured MiniMessage instance with default tags.
     * Includes standard color and formatting tags.
     */
    public static MiniMessage miniMessageSerializer = MiniMessage.builder()
            .tags(TagResolver.builder()
                    .resolver(StandardTags.defaults())
                    .build())
            .build();

    /**
     * Formats a message with replacements and optional PlaceholderAPI processing.
     * @param miniMessage MiniMessage format string
     * @param replacements Ordered values for {0} {1} style placeholders
     * @return Formatted Adventure Component
     */
    public static Component getMessage(String miniMessage, String... replacements) {
        return getMessage(null, miniMessage, replacements);
    }

    /**
     * Formats a message with player-specific placeholders.
     * @param player Target player for PlaceholderAPI (can be null)
     * @param miniMessage MiniMessage format string
     * @param replacements Ordered values for {0} {1} style placeholders
     * @return Formatted Adventure Component ready for sending
     */
    public static Component getMessage(Player player, String miniMessage, String... replacements) {
        for (int i = 0; i < replacements.length; i++) {
            miniMessage = miniMessage.replaceAll("\\{" + i + "}", replacements[i]);
        }
        miniMessage = miniMessage.stripTrailing();

        /* Auto-handles PlaceholderAPI integration when available */
        if (ServerManager.PLACEHOLDER_API)
            miniMessage = PlaceholderAPI.setPlaceholders(player, miniMessage);

        return miniMessageSerializer.deserialize(miniMessage);
    }
}