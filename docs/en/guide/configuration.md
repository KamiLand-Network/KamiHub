# Configuration Guide

> KamiHub Detailed Configuration Guide

This page provides detailed information about all KamiHub configuration options to help you customize settings according to your server needs.

## Configuration File Structure
```
plugins/KamiHub/
├── config.yml          # Main configuration file
├── messages.yml        # Message text configuration file
├── modules.yml         # Module configuration file
├── spawns.yml          # Spawn point configuration file
└── data/               # Data storage directory
```

> This plugin supports PlaceholderAPI!
> For details, please refer to: [PlaceholderAPI](papi.md)

> 99% of displayable text in this plugin uses [MiniMessage format](https://docs.advntr.dev/minimessage/format.html)
> Note: This plugin completely abandons the color mode implemented with `&` and `§` characters

### Main Configuration File (config.yml)
```yaml
# Configuration file version (not the same as plugin version)
# Do NOT change this value manually.
# The plugin uses it to detect and upgrade configuration format.
# Modifying this value may cause the plugin to malfunction.
config-version: 1.1

# Auto check for updates
check-for-updates: true

# Data source settings
datasource:
  # Storage driver type
  # Currently supported: MySQL, H2
  storage: H2
  table-prefix: 'kh_'
  mysql:
    host: 'localhost'
    port: 3306
    database: 'minecraft'
    username: 'kamihub'
    password: 'UrPasswdHere'
    params: '?useSSL=false&useUnicode=true&characterEncoding=UTF-8'
  h2:
    # H2 connection (file mode)
    file: './plugins/KamiHub/data/storage'
    username: 'kamihub'
    # H2 mode allows empty password by default.
    # If you want to enable password protection for security,
    # append this to the params value:
    # ;CIPHER=AES;IFEXISTS=TRUE
    password: ''
    params: ';MODE=MySQL;FILE_LOCK=SOCKET;TRACE_LEVEL_FILE=0;DATABASE_TO_UPPER=FALSE'
  connection-pool:
    # Connection pool settings
    # Maximum pool size (recommended: CPU cores * 2 + disk count)
    maximum-pool-size: 20
    # Minimum idle connections (recommended: 1/4 of max pool size)
    minimum-idle: 5
    # Connection timeout (ms, suggested: 3000-5000)
    connection-timeout: 3000
    # Idle timeout (ms, default: 5 minutes)
    idle-timeout: 300000
    # Maximum lifetime of a connection (ms, default: 30 minutes)
    max-lifetime: 1800000
    # Validation timeout (ms)
    validation-timeout: 1000
    # Leak detection
    # Leak detection threshold (ms)
    leak-detection-threshold: 30000
    # Recommended to disable auto-commit
    auto-commit: false
```

### Language Configuration File (messages.yml)
```yaml
prefix:
  enabled: true
  text: '<aqua>[KamiHub]</aqua> '
general:
  no-permission: '<red>You don''t have permission to use this command: <yellow>{0}'
  reload-success: '<green>Plugin reloaded successfully!'
  invalid-usage: '<red>Unknown command! Use <yellow>/kh help <red>to view command usage!'
  no-console: '<red>This command can only be executed by a player!'
  unknown-command-help: '<red>Unknown command help page!'
command-help:
  - |-
    <white>------------ <aqua>KamiHub Command Help <white>------------
    <green>/kh help [page] <gray>View help page
    <green>/kh reload <gray>Reload plugin configuration
    <green>/kh module list <gray>List all module states
    <green>/kh info <gray>Display plugin information
    <green>/kh update <gray>Check for plugin updates
    <green>/spawn <gray>Teleport to spawn
    <green>/spawn list <gray>List all spawn points
    
    <hover:show_text:'<gray>Previous Page'><gray>◀</hover>                <gray>1<white>/<green>2                <click:run_command:'/kh help 2'><hover:show_text:'<green>Next Page'><green>▶</hover></click>
  - |-
    <white>------------ <aqua>KamiHub Command Help <white>------------
    <green>/spawn add <gray>Add current location as spawn
    <green>/spawn set <gray>Set spawn point for current world
    <green>/spawn remove <gray>Remove a spawn point
    <green>/agreement <gray>Open agreement book
    <green>/agreement accept <gray>Accept player agreement
    <green>/agreement reject <gray>Reject player agreement
    <green>/agreement change <gray>Mark agreement as changed
    
    <click:run_command:'/kh help 1'><hover:show_text:'<green>Previous Page'><green>◀</hover></click>                <green>2<white>/<gray>2                <hover:show_text:'<gray>Next Page'><gray>▶</hover>
modules:
  not-found: '<red>Module not found!'
  list: '<green>Module states: {0}'
  agreement:
    accept: '<green>You have accepted the server agreement!'
    reject: '<red>You have rejected the server agreement!'
    change: '<green>Reset agreement status for all players!'
  anti-attack: '<red>You cannot attack other entities!'
  anti-break: '<red>You cannot break blocks!'
  anti-drop: '<red>You cannot drop items!'
  anti-use: '<red>You cannot interact here!'
  anti-place: '<red>You cannot place blocks here!'
  anti-projectile: '<red>You cannot shoot projectiles!'
  fly:
    enable: '<green>You have enabled fly mode!'
    disable: '<green>You have disabled fly mode!'
  spawn:
    not-found: '<red>Spawn point not found!'
    teleport: '<green>You have been teleported to spawn!'
    add: '<green>Spawn point added!'
    set: '<green>Spawn point set!'
    remove: '<green>Spawn point removed!'
    list: '<green>Current spawn points: {0}'
  void-tp: '<green>Void protection triggered!'
```

### Module Configuration File (modules.yml)

```yaml
# Module configurations
modules:
  # Action bar messages
  # Requires permission: kamihub.action-bar (granted by default)
  action-bar:
    # Whether to enable (applies similarly below)
    enabled: true
    # Refresh interval in ticks
    # In Minecraft, 1 second = 20 ticks
    # That means 1 tick = 0.05 seconds
    interval: 2
    # Texts to be shown in the action bar, displayed in order
    # All texts in this plugin use MiniMessage format. See https://docs.advntr.dev/minimessage/format.html
    # You can preview and edit MiniMessage texts here: https://webui.advntr.dev
    worlds:
      world:
        - ''
        - '<rainbow>V'
        - '<rainbow>Vi'
        - '<rainbow>Vis'
        - '<rainbow>Visi'
        - '<rainbow>Visit'
        - '<rainbow>Visit '
        - '<rainbow>Visit w'
        - '<rainbow>Visit ww'
        - '<rainbow>Visit www'
        - '<rainbow>Visit www.'
        - '<rainbow>Visit www.e'
        - '<rainbow>Visit www.ex'
        - '<rainbow>Visit www.exa'
        - '<rainbow>Visit www.exam'
        - '<rainbow>Visit www.examp'
        - '<rainbow>Visit www.exampl'
        - '<rainbow>Visit www.example'
        - '<rainbow>Visit www.example.'
        - '<rainbow>Visit www.example.c'
        - '<rainbow>Visit www.example.co'
        - '<rainbow>Visit www.example.com'
        - '<rainbow>isit www.example.com'
        - '<rainbow>sit www.example.com'
        - '<rainbow>it www.example.com'
        - '<rainbow>t www.example.com'
        - '<rainbow> www.example.com'
        - '<rainbow>www.example.com'
        - '<rainbow>ww.example.com'
        - '<rainbow>w.example.com'
        - '<rainbow>.example.com'
        - '<rainbow>example.com'
        - '<rainbow>xample.com'
        - '<rainbow>ample.com'
        - '<rainbow>mple.com'
        - '<rainbow>ple.com'
        - '<rainbow>le.com'
        - '<rainbow>e.com'
        - '<rainbow>.com'
        - '<rainbow>com'
        - '<rainbow>om'
        - '<rainbow>m'
        - ''

  # Player agreement
  agreement:
    enabled: true
    # Open agreement if player hasn't accepted it on join
    on-join: true
    # Open agreement every time player joins
    on-every-join: false
    # Open agreement when it's changed
    # Requires permission or console use of /agreement change
    on-change: true
    # Show title to players who haven't accepted the agreement
    # Available placeholders: {0} player name, {1} remaining seconds
    show-title: true
    title: '<red>You have not accepted the agreement'
    subtitle: '<gray>Please read and accept the agreement to continue'
    # Reopen agreement when player moves
    reopen-on-move: true
    # Delay (in ticks) after join before showing agreement
    delay: 100
    # Kick player if they timeout before accepting
    kick-on-timeout: true
    # Timeout duration in ticks
    timeout: 1200
    # Kick player if they reject the agreement
    kick-on-reject: true
    # Kick message to show when player is kicked
    kick-message: |-
      <red>You have not accepted the player agreement
      <red>Please read and accept to continue
    # Content of the agreement shown as book pages
    pages:
      - |-
        <bold><shadow:aqua:0.5><blue>▄ Player Agreement ▄</blue></shadow></bold>
        <gray>Welcome to the server!
        Please read and agree:
        -------------------
        <white>► Obey local laws
        ► Respect others
        ► No cheating/hacking
        ► No griefing
        ► Maintain friendly atmosphere</white>
      - |-
        <bold><shadow:aqua:0.5><blue>▄ Terms & Rules ▄</blue></shadow></bold>
        <gray>1. Must compensate for damage to others' builds
        2. No claiming public space
        3. Scamming leads to permanent ban
        4. No discriminatory language</gray>
        -------------------
        <red>Note: Punishments escalate as follows:
        Warning → Mute → Ban</red>
      - |-
        <bold><shadow:aqua:0.5><blue>▄ Declaration ▄</blue></shadow></bold>
        <gray>I have read all terms and commit to:
        • Being responsible for my actions
        • Protecting server environment
        • Accepting staff decisions</gray>

        {0}
        {1}
    # Reject button (MiniMessage)
    # You can run /agreement reject manually as well
    reject: '<click:run_command:"/agreement reject"><hover:show_text:"<gray>Click to reject"><red>Reject</hover></click>'
    # Actions to run after rejection
    # More about actions: https://docs.kamiland.net/kamihub/actions
    # Use {0} for player name
    # Use [console] prefix to run as console
    # Use [player] prefix to run as player
    reject-actions:
      - '[console]say {0} has rejected the agreement!'
    # Actions to run after acceptance
    accept-actions:
      - '[broadcast]<green>{0} has accepted the agreement!'
    # Accept button
    # Equivalent to running /agreement accept
    accept: '<click:run_command:"/agreement accept"><hover:show_text:"<gray>Click to accept"><green>Accept</hover></click>'

  # Prevent block breaking
  # Note: KamiHub is not a full-featured protection plugin
  # The developer recommends using WorldGuard: https://modrinth.com/plugin/worldguard
  # Bypass permission: kamihub.anti-break.bypass (admins by default)
  anti-break:
    enabled: true
    # Allow only creative mode players to break blocks
    # Even players with bypass can't break blocks in non-creative mode if enabled
    break-creative-only: true
    worlds:
      - world

  # Prevent using blocks
  # Bypass permission: kamihub.anti-use.bypass (admins by default)
  anti-use:
    enabled: true
    creative-only: true
    worlds:
      - world

  # Prevent block placing
  # Bypass permission: kamihub.anti-place.bypass (admins by default)
  anti-place:
    enabled: true
    creative-only: true
    worlds:
      - world

  # Prevent item dropping
  # Bypass permission: kamihub.anti-drop.bypass (admins by default)
  anti-drop:
    enabled: true
    creative-only: true
    worlds:
      - world

  # Prevent item pickup
  # Bypass permission: kamihub.anti-pickup.bypass (admins by default)
  anti-pickup:
    enabled: true
    worlds:
      - world

  # Prevent hunger
  # Requires permission: kamihub.anti-hunger (granted by default)
  anti-hunger:
    enabled: true
    worlds:
      - world

  # Prevent damage
  # Requires permission: kamihub.anti-damage (granted by default)
  anti-damage:
    enabled: true
    worlds:
      - world

  # Prevent projectiles
  # Bypass permission: kamihub.anti-projectile.bypass (admins by default)
  anti-projectile:
    enabled: true
    # Prevent players from throwing projectiles
    anti-player: false
    # Prevent mobs/entities from throwing projectiles
    anti-entity: false
    worlds:
      - world

  # Prevent attacks
  # Bypass permission: kamihub.anti-attack.bypass (admins by default)
  anti-attack:
    enabled: true
    # Prevent PvP
    anti-attack-player: true
    # Prevent attacking mobs/entities
    anti-attack-entity: true
    worlds:
      - world

  # Boss bar
  # For more on these configs, see: https://minecraft.wiki/w/Bossbar
  # Requires permission: kamihub.boss-bar (granted by default)
  boss-bar:
    enabled: true
    interval: 5
    # Format per line: <PROGRESS>:<COLOR>:<OVERLAY>:<NAME>
    # PROGRESS: 0 to 1
    # COLOR: see https://minecraft.wiki/w/Bossbar#Color
    # OVERLAY: boss bar style
    # Ensure each line has exactly 3 colons
    # Use placeholder {0} to include colons in name
    worlds:
      world:
        - '0.1:RED:NOTCHED_10:<#000080>www.example.com'
        - '0.2:RED:NOTCHED_10:<#13188B>www.example.com'
        - '0.3:RED:NOTCHED_10:<#263097>www.example.com'
        - '0.4:RED:NOTCHED_10:<#3A48A2>www.example.com'
        - '0.5:RED:NOTCHED_10:<#4D60AD>www.example.com'
        - '0.6:RED:NOTCHED_10:<#6078B8>www.example.com'
        - '0.7:RED:NOTCHED_10:<#7390C4>www.example.com'
        - '0.8:RED:NOTCHED_10:<#86A8CF>www.example.com'
        - '0.9:RED:NOTCHED_10:<#9AC0DB>www.example.com'
        - '1.0:RED:NOTCHED_10:<#ADD8E6>www.example.com'
        - '0.9:RED:NOTCHED_10:<#9AC0DB>www.example.com'
        - '0.8:RED:NOTCHED_10:<#86A8CF>www.example.com'
        - '0.7:RED:NOTCHED_10:<#7390C4>www.example.com'
        - '0.6:RED:NOTCHED_10:<#6078B8>www.example.com'
        - '0.5:RED:NOTCHED_10:<#4D60AD>www.example.com'
        - '0.4:RED:NOTCHED_10:<#3A48A2>www.example.com'
        - '0.3:RED:NOTCHED_10:<#263097>www.example.com'
        - '0.2:RED:NOTCHED_10:<#13188B>www.example.com'
        - '0.1:RED:NOTCHED_10:<#000080>www.example.com'

  # Broadcasts
  # Receive permission: kamihub.broadcast.notify (granted by default)
  broadcast:
    enabled: true
    interval: 1800
    # Using MiniMessage, you can create rich interactive broadcasts
    messages:
      - |-
        <red>Welcome to the server!</red>
        <green>Enjoy your stay!</green>
      - |-
        <red>Good luck!</red>
        <green>Have fun!</green>

  # Clear player chat
  # This works by sending 100 empty lines on join
  # Note: may not work fully with mods like Chat Filter or Infinite History
  # Use F3+D to fully clear chat client-side
  # Bypass permission: kamihub.clear-chat.bypass (admins by default)
  clear-chat:
    enabled: true
    # Clear on player join
    on-join: true
    
  # Lobby flight feature
  # Requires permission: kamihub.fly
  fly:
    enabled: true
    # Enable automatic flight for players when they join the server
    auto-fly-on-join: true
    worlds:
      - world

  # Inventory control
  # Bypass permission: ultimate.inventory.bypass (admins by default)
  inventory:
    enabled: true
    # Clear inventory on join
    # Disabled by default to avoid accidents
    clear-on-join: false
    # Clear inventory on quit
    clear-on-quit: false
    # Give defined items on join (see items.yml)
    give-on-join: false
    # Prevent inventory movement
    prevent-moving: true

  # Join/quit messages
  # Some servers show different messages per group
  join-quit-message:
    enabled: true
    # Group-based message configuration
    # Use {0} as player name placeholder
    # Currently supports LuckPerms and Vault-compatible permission plugins
    # If not using Vault, use permission: kamihub.group.xxx
    groups:
      default:
        # Group priority
        # Higher priority group takes effect when in multiple
        priority: 0
        join:
          - '<green>[+] <reset>{0}'
        quit:
          - '<gray>[-] <reset>{0}'
      vip:
        priority: 10
        join:
          - '<rainbow>{0} joined the server!</rainbow>'
        quit:
          - '<rainbow:!>{0} left the server.</rainbow>'

  # Potion effects
  # Requires permission: kamihub.potion-effect (admins by default)
  potion-effect:
    enabled: true
    # Clear potion effects on join
    clear-on-join: true
    # Clear potion effects on quit
    clear-on-quit: true
    # Give potion effects on join
    give-on-join: true
    # Potion effect list
    # Use effect identifiers from: https://minecraft.wiki/w/Effect#Effect_list
    effects:
      - type: speed
        # Duration in seconds; use 'infinite' for infinite duration
        duration: 'infinite'
        # Amplifier is effect level minus 1 (1 = level II)
        amplifier: 1
        # Show particles
        particles: false
      - type: jump_boost
        duration: '60'
        amplifier: 0
        particles: true

  # Spawn point
  # Requires permission: kamihub.spawn (granted by default)
  # Configuration in spawns.yml
  spawn:
    enabled: true
    # Teleport player to spawn on join
    on-join: true

  # Void teleport protection
  # Requires permission: kamihub.void-tp (granted by default)
  void-teleport:
    enabled: true
    # Level below which players are teleported
    # Destination is: custom spawn > respawn point > default world spawn
    level: -64
    worlds:
      - world
```

### Spawn Point Configuration File (spawns.yml)
```yaml
spawns:
  main:
    name: 'main'
    x: 0.0
    y: 64.0
    z: 0.0
    yaw: 0.0
    pitch: 0.0
    world: 'world'
  example:
    name: 'example'
    x: 5.0
    y: 64.0
    z: 5.0
    yaw: 0.0
    pitch: 0.0
    world: "world"
```
