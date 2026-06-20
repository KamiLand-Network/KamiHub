# Permission Configuration

> KamiHub Permission Nodes Reference

This page provides a comprehensive list of all permission nodes used by KamiHub, their default values, and descriptions.

## Permission Overview

KamiHub uses a hierarchical permission system where:
- **Default**: Automatically granted to all players
- **Admin**: Requires explicit permission assignment (typically for operators/administrators)
- **Wildcard**: `kamihub.*` grants all KamiHub permissions

## Basic Permission Nodes

| Permission | Default | Description |
|:-------|:----:|-------:|
| `kamihub.*` | Admin | Contains all KamiHub permission nodes |
| `kamihub.agreement.change` | Admin | Change player agreement |
| `kamihub.action-bar` | Default | Permission to display action bar |
| `kamihub.anti-break.bypass` | Admin | Bypass block breaking protection |
| `kamihub.anti-use.bypass` | Admin | Bypass item usage protection |
| `kamihub.anti-place.bypass` | Admin | Bypass block placement protection |
| `kamihub.anti-drop.bypass` | Admin | Bypass item dropping protection |
| `kamihub.anti-pickup.bypass` | Admin | Bypass item pickup protection |
| `kamihub.anti-hunger` | Default | Prevent hunger level decrease |
| `kamihub.anti-damage` | Default | Prevent taking damage |
| `kamihub.anti-projectile.bypass` | Admin | Bypass projectile protection |
| `kamihub.anti-attack.bypass` | Admin | Bypass attack protection |
| `kamihub.boss-bar` | Default | Permission to display boss bar |
| `kamihub.broadcast.notify` | Default | Receive broadcast notifications |
| `kamihub.clear-chat.bypass` | Admin | Bypass chat clearing |
| `kamihub.inventory.bypass` | Admin | Bypass inventory protection |
| `kamihub.potion-effect` | Admin | Potion effect management permission |
| `kamihub.spawn` | Default | Teleport to spawn point |
| `kamihub.spawn.add` | Admin | Add spawn points |
| `kamihub.spawn.set` | Admin | Set spawn points |
| `kamihub.spawn.remove` | Admin | Remove spawn points |
| `kamihub.spawn.list` | Admin | View spawn point list |
| `kamihub.void-tp` | Default | Void teleport protection |
| `kamihub.help` | Admin | View help information |
| `kamihub.reload` | Admin | Reload plugin configuration |
| `kamihub.module.list` | Admin | View module list |
| `kamihub.update` | Admin | Update plugin |

## Permission Categories

### Core Management
These permissions control basic plugin functionality:
- `kamihub.help` - Access to help commands
- `kamihub.reload` - Reload plugin configuration
- `kamihub.update` - Check for plugin updates
- `kamihub.module.list` - List module status

### Player Protection
These permissions provide various protection features:
- `kamihub.anti-damage` - Immunity to damage
- `kamihub.anti-hunger` - Immunity to hunger
- `kamihub.void-tp` - Automatic void teleport protection

### Anti-Grief System
These permissions control grief protection bypasses:
- `kamihub.anti-break.bypass` - Bypass block breaking restrictions
- `kamihub.anti-place.bypass` - Bypass block placement restrictions
- `kamihub.anti-use.bypass` - Bypass item usage restrictions
- `kamihub.anti-drop.bypass` - Bypass item dropping restrictions
- `kamihub.anti-pickup.bypass` - Bypass item pickup restrictions
- `kamihub.anti-attack.bypass` - Bypass attack restrictions
- `kamihub.anti-projectile.bypass` - Bypass projectile restrictions

### Display Features
These permissions control visual elements:
- `kamihub.action-bar` - Receive action bar messages
- `kamihub.boss-bar` - Receive boss bar messages
- `kamihub.broadcast.notify` - Receive broadcast notifications

### Spawn Management
These permissions control spawn point functionality:
- `kamihub.spawn` - Use spawn teleport
- `kamihub.spawn.add` - Add new spawn points
- `kamihub.spawn.set` - Modify existing spawn points
- `kamihub.spawn.remove` - Delete spawn points
- `kamihub.spawn.list` - View all spawn points

### Agreement System
These permissions control the player agreement system:
- `kamihub.agreement.change` - Mark agreement as changed (forces re-acceptance)

## Permission Setup Examples

### LuckPerms Configuration
```bash
# Grant all KamiHub permissions to administrators
/lp group admin permission set kamihub.* true

# Grant specific permissions to moderators
/lp group moderator permission set kamihub.spawn.* true
/lp group moderator permission set kamihub.anti-break.bypass true

# Grant basic permissions to players (usually automatic)
/lp group default permission set kamihub.spawn true
/lp group default permission set kamihub.action-bar true
```

### PermissionsEx Configuration
```yaml
groups:
  admin:
    permissions:
      - kamihub.*
  moderator:
    permissions:
      - kamihub.spawn.*
      - kamihub.anti-break.bypass
      - kamihub.anti-place.bypass
  default:
    permissions:
      - kamihub.spawn
      - kamihub.action-bar
      - kamihub.boss-bar
```

## Default Permission Behavior

### Automatically Granted (Default: true)
These permissions are automatically granted to all players:
- Basic display features (action-bar, boss-bar)
- Player protection features (anti-damage, anti-hunger)
- Basic teleport functionality (spawn, void-tp)
- Broadcast notifications

### Requires Assignment (Default: false)
These permissions must be explicitly granted:
- Administrative commands (reload, update, help)
- Spawn management (add, remove, set, list)
- Protection bypasses (all anti-* bypass permissions)
- Agreement management

## Best Practices

1. **Use Group-Based Permissions**: Assign permissions to groups rather than individual players
2. **Principle of Least Privilege**: Only grant necessary permissions
3. **Regular Audits**: Periodically review permission assignments
4. **Test Permissions**: Verify permission setups in a test environment
5. **Document Changes**: Keep track of permission modifications

---

*For more information about configuring permissions with specific permission plugins, consult their respective documentation.*
