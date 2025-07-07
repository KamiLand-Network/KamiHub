package net.kamiland.ultimatehub.module;

import org.jetbrains.annotations.Nullable;

/**
 * Core interface for plugin modules. All functional components should implement
 * this interface to integrate with the module management system.
 *
 * @author while1cry
 */
public interface Module {

    /**
     * Gets the unique identifier for this module.
     *
     * @return Module name used in configuration and command systems
     */
    String getName();

    /**
     * Checks the activation status of the module.
     *
     * @return true if the module is currently enabled
     */
    boolean isEnabled();

    /**
     * Updates the module's activation state.
     *
     * @param enabled New activation state (true=active, false=disabled)
     */
    void setEnabled(boolean enabled);

    /**
     * Initializes module functionality. Called when the module is enabled.
     * Implementations should register listeners and schedule tasks here.
     */
    void setup();

    /**
     * Gets the required permission node for this module.
     *
     * @return Permission string, or null if no permission is required
     */
    @Nullable String getPermission();

    /**
     * Gets the bypass permission that overrides module restrictions.
     *
     * @return Bypass permission string, or null if no bypass available
     */
    @Nullable String getBypassPermission();

}