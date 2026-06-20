# 配置说明

> KamiHub 详细配置指南

本页面详细介绍KamiHub的所有配置选项，帮助您根据服务器需求进行个性化设置。

## 配置文件结构
```
plugins/KamiHub/
├── config.yml          # 主配置文件
├── messages.yml        # 消息文本配置文件
├── modules.yml         # 模块配置文件
├── spawns.yml          # 出生点配置文件
└── data/               # 数据存储目录
```

> 该插件支持 PlaceholderAPI！
> 详情请参阅：[PlaceholderAPI](papi.md)

> 该插件 99% 可显示文本使用[MiniMessage格式](https://docs.advntr.dev/minimessage/format.html)
> 注：该插件完全弃用了以`&`和`§`字符实现的颜色模式

### 主配置文件 (config.yml)
```yaml
# 配置文件版本号，并不等于插件版本
# 请不要更改此项，插件会自动检测并更新配置文件版本
# 如果你更改了此项，插件可能无法正常工作
config-version: 1.1

# 自动检查更新
check-for-updates: true

# 数据存储设置
datasource:
  # 数据驱动类型
  # 目前支持：MySQL，H2
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
    # H2连接（文件模式）
    file: './plugins/KamiHub/data/storage'
    username: 'kamihub'
    # H2模式允许（默认）不设置密码
    # 如果您希望启用密码以提升安全性，请在params值后面添加：;CIPHER=AES;IFEXISTS=TRUE
    password: ''
    params: ';MODE=MySQL;FILE_LOCK=SOCKET;TRACE_LEVEL_FILE=0;DATABASE_TO_UPPER=FALSE'
  connection-pool:
    # 连接池控制
    # 最大连接数（推荐公式：CPU核心数*2 + 磁盘数）
    maximum-pool-size: 20
    # 最小空闲连接（推荐设为max-pool-size的1/4）
    minimum-idle: 5
    # 连接超时时间（毫秒，建议3000-5000）
    connection-timeout: 3000
    # 空闲超时（毫秒，5分钟）
    idle-timeout: 300000
    # 连接最大存活时间（毫秒，30分钟）
    max-lifetime: 1800000
    # 验证超时（毫秒）
    validation-timeout: 1000
    # 泄漏检测
    # 泄漏检测阈值
    leak-detection-threshold: 30000
    # 推荐关闭自动提交
    auto-commit: false
```

### 语言配置文件 (messages.yml)
```yaml
prefix:
  enabled: true
  text: '<aqua>[KamiHub]</aqua> '
general:
  no-permission: '<red>你没有权限执行此命令: <yellow>{0}'
  reload-success: '<green>插件重载成功！'
  invalid-usage: '<red>未知的命令！请使用 <yellow>/kh help <red>查看命令用法！'
  no-console: '<red>该命令只能由玩家执行！'
  unknown-command-help: '<red>未知的命令！'
command-help:
  - |-
    <white>------------ <aqua>KamiHub 命令帮助 <white>------------
    <green>/kh help [页数] <gray>查看帮助页
    <green>/kh reload <gray>重载插件配置
    <green>/kh module list <gray>列出所有模块状态
    <green>/kh info <gray>显示插件信息
    <green>/kh update <gray>检查插件更新
    <green>/spawn <gray>传送至出生点
    <green>/spawn list <gray>列出出生点
    
    <hover:show_text:'<gray>上一页'><gray>◀</hover>                <gray>1<white>/<green>2                <click:run_command:'/kh help 2'><hover:show_text:'<green>下一页'><green>▶</hover></click>
  - |-
    <white>------------ <aqua>KamiHub 命令帮助 <white>------------
    <green>/spawn add <gray>添加当前位置为出生点
    <green>/spawn set <gray>设置当前世界出生点
    <green>/spawn remove <gray>移除出生点
    <green>/agreement <gray>打开协议书
    <green>/agreement accept <gray>接受玩家协议
    <green>/agreement reject <gray>拒绝玩家协议
    <green>/agreement change <gray>标记协议已更改
    
    <click:run_command:'/kh help 1'><hover:show_text:'<green>上一页'><green>◀</hover></click>                <green>2<white>/<gray>2                <hover:show_text:'<gray>下一页'><gray>▶</hover>
modules:
  not-found: '<red>未找到该模块！'
  list: '<green>当前所有的模块：{0}'
  enabled: '<yellow>{0} 模块已启用！'
  disabled: '<yellow>{0} 模块已禁用！'
  agreement:
    accept: '<green>你已同意本服务器的协议！'
    reject: '<red>你已拒绝本服务器的协议！'
    change: '<green>已重置每个玩家的协议状态！'
  anti-attack: '<red>你不能攻击其他玩家！'
  anti-break: '<red>你不能破坏方块！'
  anti-drop: '<red>你不能丢弃物品！'
  anti-use: '<red>你不能在这里交互！'
  anti-place: '<red>你不能在这里放置方块！'
  anti-projectile: '<red>你不能发射投掷物！'
  fly:
    enable: '<green>你启用了飞行模式！'
    disable: '<green>你禁用了飞行模式！'
  spawn:
    not-found: '<red>未找到该出生点！'
    teleport: '<green>你已传送到出生点！'
    add: '<green>已添加出生点！'
    set: '<green>已设置出生点！'
    remove: '<green>已移除出生点！'
    list: '<green>当前所有的出生点：{0}'
  void-tp: '<green>虚空保护已生效！'
```

### 模块配置文件 (modules.yml)

```yaml
# 各模块配置
modules:
  # 动作栏信息
  # 需要权限：kamihub.action-bar（默认拥有）
  action-bar:
    # 是否启用，后同
    enabled: true
    # 刷新间隔，以tick为单位
    # 在Minecraft中，1秒流逝20ticks
    # 也就是1tick = 0.05秒
    interval: 2
    # action bar显示的文本，按照顺序依次显示
    # 本插件所有的文本格式都为MiniMessage，详参 https://docs.advntr.dev/minimessage/format.html
    # 你可以在这里在线编辑和预览MiniMessage格式的文本：https://webui.advntr.dev
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

  # 玩家协议
  agreement:
    enabled: true
    # 在玩家加入且还未同意协议的时候打开协议
    on-join: true
    # 在玩家每次加入的时候打开协议
    on-every-join: false
    # 在协议发生更改的时候打开协议
    # 此项需要有权限的玩家/控制台使用指令 /agreement change
    on-change: true
    # 向未同意协议的玩家显示 title
    # 可用占位符：{0} 玩家名称， {1} 剩余秒数
    show-title: true
    title: '<red>你还没有同意玩家协议'
    subtitle: '<gray>请阅读并同意玩家协议以继续游玩'
    # 当玩家未同意并移动时重新打开协议书
    reopen-on-move: true
    # 从玩家进入服务器到打开协议的间隔，以tick为单位
    delay: 100
    # 如果玩家签订协议超时，是否踢出
    kick-on-timeout: true
    # 签订协议超时的时间，以tick为单位
    timeout: 1200
    # 如果玩家拒绝协议，是否踢出
    kick-on-reject: true
    # 如果踢出玩家，踢出的原因（信息）
    kick-message: |-
      <red>你还没有接受玩家协议
      <red>请阅读并接受玩家协议以继续游玩
    # 协议以书本形式展示的每一页内容
    pages:
      - |-
        <bold><shadow:aqua:0.5><blue>▄玩家协议▄</blue></shadow></bold>
        <gray>欢迎加入服务器！
        请仔细阅读并同意：
        -------------------
        <white>► 遵守当地法律法规
        ► 尊重其他玩家
        ► 禁止作弊/外挂
        ► 禁止恶意破坏建筑
        ► 保持友善的交流氛围</white>
      - |-
        <bold><shadow:aqua:0.5><blue>▄条款细则▄</blue></shadow></bold>
        <gray>1. 破坏他人建筑需赔偿
        2. 公共区域禁止占位
        3. 交易诈骗永久封禁
        4. 严禁种族歧视言论
        5. 资源世界每周重置</gray>
        -------------------
        <red>注：违规者视情节给予
        警告→禁言→封禁处罚</red>
      - |-
        <bold><shadow:aqua:0.5><blue>▄签署声明▄</blue></shadow></bold>
        <gray>我已阅读全部条款并承诺：
        • 对自己的行为负责
        • 维护服务器环境
        • 接受管理员裁决</gray>

        {0}
        {1}
    # 拒绝按钮
    # 如你所见，这是一个典型的MiniMessage格式，详参上文
    # 你可以让玩家执行 /agreement reject 来拒绝协议，这是等效的
    reject: '<click:run_command:"/agreement reject"><hover:show_text:"<gray>点击拒绝玩家协议"><red>拒绝</hover></click>'
    # 拒绝后执行Actions
    # 更多行为，详见：https://docs.kamiland.net/zh/kamihub/actions
    # 使用 {0} 作为玩家名称
    # 使用 [console] 开头，以控制台身份执行指令
    # 使用 [player] 开头，以玩家身份执行指令
    reject-actions:
      - '[console]say {0}已拒绝玩家协议！'
    # 同意后执行Actions
    # 使用 {0} 作为玩家名称
    accept-actions:
      - '[broadcast]<green>{0}已同意玩家协议！'
    # 同意按钮
    # 你可以让玩家执行 /agreement accept 来同意协议，这是等效的
    accept: '<click:run_command:"/agreement accept"><hover:show_text:"<gray>点击同意玩家协议"><green>同意</hover></click>'

  # 防止玩家破坏方块
  # 一些写在前面的话：
  # KamiHub 并不是一个专业的世界保护插件，它并不具备完善的世界保护功能
  # 开发者对游戏内容欠缺理解，可能存在某些游戏特性可以让玩家绕过这些保护
  # 因此，开发者推荐您采用专业的世界保护插件，如开源的 WorldGuard: https://modrinth.com/plugin/worldguard
  # 绕过权限: kamihub.anti-break.bypass（默认管理员拥有）
  anti-break:
    enabled: true
    # 是否仅允许创造模式玩家破坏方块
    # 如果开启，即使是拥有绕过权限的玩家，在非创造模式下也无法破坏方块
    # 如果开启，没有绕过权限的创造模式玩家依旧不可以破坏方块
    # 下同
    break-creative-only: true
    worlds:
      - world

  # 防止玩家使用方块
  # 绕过权限：kamihub.anti-use.bypass（默认管理员拥有）
  anti-use:
    enabled: true
    creative-only: true
    worlds:
      - world

  # 防止玩家放置
  # 绕过权限：kamihub.anti-place.bypass（默认管理员拥有）
  anti-place:
    enabled: true
    creative-only: true
    worlds:
      - world

  # 防止玩家丢弃物品
  # 绕过权限：kamihub.anti-drop.bypass（默认管理员拥有）
  anti-drop:
    enabled: true
    creative-only: true
    worlds:
      - world

  # 防止玩家拾取物品
  # 绕过权限：kamihub.anti-pickup.bypass（默认管理员拥有）
  anti-pickup:
    enabled: true
    worlds:
      - world

  # 防止饥饿
  # 需要权限：kamihub.anti-hunger（默认拥有）
  anti-hunger:
    enabled: true
    worlds:
      - world

  # 防止玩家受伤
  # 需要权限：kamihub.anti-damage（默认拥有）
  anti-damage:
    enabled: true
    worlds:
      - world

  # 防止投掷物
  # 绕过权限：kamihub.anti-projectile.bypass（默认管理员拥有）
  anti-projectile:
    enabled: true
    # 防止玩家射出投掷物
    anti-player: false
    # 防止生物射出投掷物
    anti-entity: false
    worlds:
      - world

  # 防止玩家攻击
  # 绕过权限：kamihub.anti-attack.bypass（默认管理员拥有）
  anti-attack:
    enabled: true
    # 防止玩家攻击玩家（PVP）
    anti-attack-player: true
    # 防止玩家攻击生物（实体）（PVE）
    anti-attack-entity: true
    worlds:
      - world

  # Boss血条栏
  # 有关这些配置更进一步的了解，请参阅：https://minecraft.wiki/w/Bossbar
  # 需要权限：kamihub.boss-bar（默认拥有）
  boss-bar:
    enabled: true
    interval: 5
    # Boss bar显示的名字
    # 每行格式按照：<PROGRESS>:<COLOR>:<OVERLAY>:<NAME>的顺序
    # PROGRESS: Boss bar的进度，范围为0-1
    # COLOR: Boss bar的颜色，详见 https://minecraft.wiki/w/Bossbar#Color
    # OVERLAY: Boss bar的样式
    # 请确保每一行有且仅有3个冒号，分成4部分
    # 如果您想在Boss名中使用冒号，使用占位符 {0}
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

  # 广播
  # 接收广播的权限：kamihub.broadcast.notify（默认拥有）
  broadcast:
    enabled: true
    interval: 1800
    # 如果您合理的使用MiniMessage，完全可以实现拥有复杂点击和显示效果的广播
    messages:
      - |-
        <red>Welcome to the server!</red>
        <green>Enjoy your stay!</green>
      - |-
        <red>Good luck!</red>
        <green>Have fun!</green>

  # 清空玩家聊天栏
  # 该模块的原理是在玩家进入服务器时发送100条空消息，强制玩家看不见以前的消息
  # 注意：这意味着该模块不一定完全有效，尤其是在玩家安装了有Chat Filter或Infinite Chat History之类功能的模组时
  # 如果您的确需要清空聊天栏，请在游戏内使用 F3+D 快捷键，这是Minecraft的官方功能
  # 绕过权限：kamihub.clear-chat.bypass（默认管理员拥有）
  clear-chat:
    enabled: true
    # 在玩家加入时清空
    on-join: true

  # 大厅飞行功能
  # 需求权限：kamihub.fly
  fly:
    enabled: true
    # 玩家加入服务器时自动飞行
    auto-fly-on-join: true
    worlds:
      - world

  # 物品栏模块
  # 绕过权限：ultimate.inventory.bypass（默认管理员拥有）
  inventory:
    enabled: true
    # 在玩家加入服务器时清空物品栏
    # 默认关闭，因为并不是所有服务器都需要这个功能，我们不希望出现惨案
    clear-on-join: false
    # 在玩家退出服务器时清空物品栏
    clear-on-quit: false
    # 在玩家加入服务器时给予定义的物品，详见 items.yml
    give-on-join: false
    # 防止玩家移动物品栏
    prevent-moving: true

  # 加入和退出消息
  # 一些服务器可能根据不同的权限组显示不同的加入/退出消息
  join-quit-message:
    enabled: true
    # 分组配置加入和退出消息
    # 使用占位符 {0} 作为玩家名
    # 目前仅支持LuckPerms和支持Vault的权限插件
    # 如果您的权限插件不支持Vault，请为您的组添加 kamihub.group.xxx 权限来声明权限组
    groups:
      # 默认权限组，不可删除
      default:
        # 权限组权重
        # 当一个玩家同时处于多个用户组时，KamiHub会选择玩家拥有的权限组中权重最大的那个
        priority: 0
        # 加入和退出消息
        # 当加入和退出消息为多条时，随机选择一条
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

  # 药水效果
  # 需要权限：kamihub.potion-effect（默认管理员拥有）
  potion-effect:
    enabled: true
    # 是否在玩家加入时清除药水效果
    clear-on-join: true
    # 是否在玩家退出时清除药水效果
    clear-on-quit: true
    # 是否在玩家加入时给予以下药水效果
    give-on-join: true
    # 药水效果列表
    # 关于药水效果名称，详参：https://minecraft.wiki/w/Effect#Effect_list
    # 请使用wiki中药水效果的identifier
    effects:
      - type: speed
        # 药水效果时长，请使用秒为单位，使用 infinite 表示无限时长
        duration: 'infinite'
        # 药水效果倍数，这里的倍数为显示倍数-1
        # 如果你希望获得速度II的效果，这里应该填写1
        amplifier: 1
        # 是否显示粒子效果
        particles: false
      - type: jump_boost
        duration: '60'
        amplifier: 0
        particles: true

  # 出生点
  # 需要权限：kamihub.spawn（默认拥有）
  # 有关出生点的设置，详见 spawns.yml
  spawn:
    enabled: true
    # 在玩家加入时传送至出生点
    on-join: true

  # 虚空保护
  # 需要权限：kamihub.void-tp（默认拥有）
  void-teleport:
    enabled: true
    # 保护层级，当玩家所处位置低于这个层级时，将被传送回
    # - 出生点（如果设置）
    # - 重生点（床或重生锚，如果设置）
    # - 原版出生点（以上都没有设置）
    level: -64
    worlds:
      - world
```

### 出生点配置文件 (spawns.yml)
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
