# Commands Reference

> KamiHub Complete Command List

This page provides detailed descriptions of all KamiHub plugin commands, including syntax, parameters, permission requirements, and usage examples.

## Command Overview

### Command Categories
- **Core Commands**: Plugin management and basic functions
- **Spawn Teleport**: Teleport point management and teleport functions
- **Player Agreement**: Agreement management and signing functions

### Command Format Description
```
<required parameter>     # Required parameter
[optional parameter]     # Optional parameter
{choice parameter}       # Choose one from multiple options
...                     # Can have multiple parameters of the same type
```

## Core Commands

### Main Command `/kamihub`
> Aliases: `/khub`, `/kh`

#### Basic Information
```bash
# Display plugin information and version
/kamihub info

# Check for updates via GitHub
# Requires permission: kamihub.update
/kamihub update

# Display help information
# Requires permission: kamihub.help
/kamihub help [page]
```

#### Plugin Management
```bash
# Reload configuration
# Requires permission: kamihub.reload
/kamihub reload

# List all modules and their enabled status
# Requires permission: kamihub.module.list
/kamihub modules list
```

#### Player Agreement
```bash
# Accept agreement
/agreement accept

# Reject agreement
/agreement reject

# Change agreement
# Requires permission: kamihub.agreement.change
/agreement change
```

#### Spawn Point Teleport Commands
```bash
# Teleport to spawn point
# Requires permission: kamihub.spawn
/spawn

# Add spawn point
# Requires permission: kamihub.spawn.add
/spawn add <name>

# Remove spawn point
# Requires permission: kamihub.spawn.remove
/spawn remove <name>

# Set spawn point
# Requires permission: kamihub.spawn.set
/spawn set <name>

# List all spawn points
# Requires permission: kamihub.spawn.list
/spawn list
```
