# Module System Overview

> KamiHub's Core Architecture - Modular Design

KamiHub's module design aims to provide features that can be enabled and disabled at any time, ensuring that disabled modules consume almost no resources.

Module hot enable/disable functionality is expected to be available in version 1.1, with custom module functionality for developers to be opened in the future.

## Currently Implemented Modules

According to `modules.yml` configuration, here are all current modules:

| Module Name | Default Enabled | Notes |
|-------------|-----------------|-------|
| action-bar | true | Action bar information |
| agreement | true | Player agreement |
| anti-break | true | Prevent players from breaking blocks |
| anti-use | true | Prevent players from using blocks |
| anti-place | true | Prevent players from placing blocks |
| anti-drop | true | Prevent players from dropping items |
| anti-pickup | true | Prevent players from picking up items |
| anti-hunger | true | Prevent hunger |
| anti-damage | true | Prevent player damage |
| anti-projectile | true | Prevent projectiles |
| anti-attack | true | Prevent player attacks |
| boss-bar | true | Boss bar |
| broadcast | true | Broadcast |
| clear-chat | true | Clear player chat |
| inventory | true | Inventory module |
| join-quit-message | true | Join and quit messages |
| potion-effect | true | Potion effects |
| spawn | true | Spawn points |
| void-teleport | true | Void protection |

KamiHub adopts a modular architecture where each feature is designed as an independent module that can be flexibly enabled or disabled according to server needs. This design not only improves the plugin's performance and stability but also provides server administrators with great customization space.

## Architecture Design

### Core Concepts
- **Loose Coupling**: Modules are independent of each other and do not interfere with each other
- **Pluggable**: Support dynamic loading and unloading of modules
- **High Performance**: Each module is performance optimized
- **Easy to Extend**: Open API interfaces support third-party module development

You can view detailed documentation and configuration instructions for each module through the left navigation.
