# 模块系统总览

> KamiHub的核心架构 - 模块化设计

KamiHub的模块设计旨在提供可随时启用和禁用的功能，以保证任何模块禁用时几乎不占据资源。

预计在1.1版本可使用模块热启用/禁用功能，未来可开放供开发者的自定义模块功能。

## 当前实现的模块

根据 `modules.yml` 配置，以下是当前所有模块：

| 模块名 | 默认启用 | 备注 |
|--------|----------|------|
| action-bar | true | 动作栏信息 |
| agreement | true | 玩家协议 |
| anti-break | true | 防止玩家破坏方块 |
| anti-use | true | 防止玩家使用方块 |
| anti-place | true | 防止玩家放置方块 |
| anti-drop | true | 防止玩家丢弃物品 |
| anti-pickup | true | 防止玩家拾取物品 |
| anti-hunger | true | 防止饥饿 |
| anti-damage | true | 防止玩家受伤 |
| anti-projectile | true | 防止投掷物 |
| anti-attack | true | 防止玩家攻击 |
| boss-bar | true | Boss血条栏 |
| broadcast | true | 广播 |
| clear-chat | true | 清空玩家聊天栏 |
| inventory | true | 物品栏模块 |
| join-quit-message | true | 加入和退出消息 |
| potion-effect | true | 药水效果 |
| spawn | true | 出生点 |
| void-teleport | true | 虚空保护 |

KamiHub采用模块化架构，每个功能都被设计为独立的模块，可以根据服务器需求灵活启用或禁用。这种设计不仅提高了插件的性能和稳定性，还为服务器管理员提供了极大的自定义空间。

## 架构设计

### 核心理念
- **松耦合**：各模块之间相互独立，互不干扰
- **可插拔**：支持动态加载和卸载模块
- **高性能**：每个模块都经过性能优化
- **易扩展**：开放的API接口，支持第三方模块开发

通过左侧导航可以查看各个模块的详细文档和配置说明。
