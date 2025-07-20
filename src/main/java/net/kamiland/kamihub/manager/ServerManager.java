package net.kamiland.kamihub.manager;

import net.kamiland.kamihub.KamiHub;

/**
 * Server environment manager for dependency checks and version control
 *
 * <p>Handles server-side environment detection during plugin initialization:
 * <ul>
 *   <li>Verifies plugin dependencies (e.g. PlaceholderAPI)</li>
 *   <li>Detects server software type (Paper/Spigot)</li>
 *   <li>Performs version compatibility checks</li>
 * </ul>
 *
 * @author while1cry
 */
public class ServerManager {

    private final KamiHub plugin;

    /**
     * Status flag for PlaceholderAPI availability
     */
    public static boolean PLACEHOLDER_API;

    /**
     * Status flag for LuckPerms availability
     */
    public static boolean LUCKPERMS;


    /**
     * Status flag for Vault availability
     */
    public static boolean VAULT;

    /**
     * Constructs server manager with plugin instance
     * @param plugin Main plugin instance for server access
     */
    public ServerManager(KamiHub plugin) {
        this.plugin = plugin;
    }

    /**
     * Loads server environment information
     * <p>Executes during plugin enable phase to:
     * <ol>
     *   <li>Check essential dependencies</li>
     *   <li>Validate server version compatibility</li>
     *   <li>Initialize version-specific adapters</li>
     * </ol>
     */
    public void load() {
        PLACEHOLDER_API = checkPlugin("PlaceholderAPI");
        LUCKPERMS = checkPlugin("LuckPerms");
        VAULT = checkPlugin("Vault");
    }

    /**
     * Checks if specified plugin is present
     * @param name Exact plugin name to check (case-sensitive)
     * @return true if plugin exists and is enabled
     */
    public boolean checkPlugin(String name) {
        return plugin.getServer().getPluginManager().getPlugin(name) != null;
    }

}