# 权限配置

> KamiHub 权限节点参考

本页面提供了 KamiHub 使用的所有权限节点的完整列表、默认值和描述。

## 权限概述

KamiHub 使用分层权限系统，其中：
- **默认**：自动授予所有玩家
- **管理**：需要显式权限分配（通常用于操作员/管理员）
- **通配符**：`kamihub.*` 授予所有 KamiHub 权限

## 基础权限节点

| 权限 | 默认拥有 | 描述 |
|:-------|:----:|-------:|
| `kamihub.*` | 管理 | 包含所有 KamiHub 权限节点 |
| `kamihub.agreement.change` | 管理 | 更改玩家协议 |
| `kamihub.action-bar` | 默认 | 显示动作栏的权限 |
| `kamihub.anti-break.bypass` | 管理 | 绕过方块破坏保护 |
| `kamihub.anti-use.bypass` | 管理 | 绕过物品使用保护 |
| `kamihub.anti-place.bypass` | 管理 | 绕过方块放置保护 |
| `kamihub.anti-drop.bypass` | 管理 | 绕过物品丢弃保护 |
| `kamihub.anti-pickup.bypass` | 管理 | 绕过物品拾取保护 |
| `kamihub.anti-hunger` | 默认 | 防止饥饿值下降 |
| `kamihub.anti-damage` | 默认 | 防止受到伤害 |
| `kamihub.anti-projectile.bypass` | 管理 | 绕过投射物保护 |
| `kamihub.anti-attack.bypass` | 管理 | 绕过攻击保护 |
| `kamihub.boss-bar` | 默认 | 显示Boss栏的权限 |
| `kamihub.broadcast.notify` | 默认 | 接收广播通知 |
| `kamihub.clear-chat.bypass` | 管理 | 绕过聊天清理 |
| `kamihub.inventory.bypass` | 管理 | 绕过背包保护 |
| `kamihub.potion-effect` | 管理 | 药水效果管理权限 |
| `kamihub.spawn` | 默认 | 传送到出生点 |
| `kamihub.spawn.add` | 管理 | 添加出生点 |
| `kamihub.spawn.set` | 管理 | 设置出生点 |
| `kamihub.spawn.remove` | 管理 | 删除出生点 |
| `kamihub.spawn.list` | 管理 | 查看出生点列表 |
| `kamihub.void-tp` | 默认 | 虚空传送保护 |
| `kamihub.help` | 管理 | 查看帮助信息 |
| `kamihub.reload` | 管理 | 重载插件配置 |
| `kamihub.module.list` | 管理 | 查看模块列表 |
| `kamihub.update` | 管理 | 更新插件 |

## 权限分类

### 核心管理
这些权限控制基本插件功能：
- `kamihub.help` - 访问帮助命令
- `kamihub.reload` - 重载插件配置
- `kamihub.update` - 检查插件更新
- `kamihub.module.list` - 列出模块状态

### 玩家保护
这些权限提供各种保护功能：
- `kamihub.anti-damage` - 免疫伤害
- `kamihub.anti-hunger` - 免疫饥饿
- `kamihub.void-tp` - 自动虚空传送保护

### 反破坏系统
这些权限控制破坏保护绕过：
- `kamihub.anti-break.bypass` - 绕过方块破坏限制
- `kamihub.anti-place.bypass` - 绕过方块放置限制
- `kamihub.anti-use.bypass` - 绕过物品使用限制
- `kamihub.anti-drop.bypass` - 绕过物品丢弃限制
- `kamihub.anti-pickup.bypass` - 绕过物品拾取限制
- `kamihub.anti-attack.bypass` - 绕过攻击限制
- `kamihub.anti-projectile.bypass` - 绕过投射物限制

### 显示功能
这些权限控制视觉元素：
- `kamihub.action-bar` - 接收动作栏消息
- `kamihub.boss-bar` - 接收Boss栏消息
- `kamihub.broadcast.notify` - 接收广播通知

### 出生点管理
这些权限控制出生点功能：
- `kamihub.spawn` - 使用出生点传送
- `kamihub.spawn.add` - 添加新出生点
- `kamihub.spawn.set` - 修改现有出生点
- `kamihub.spawn.remove` - 删除出生点
- `kamihub.spawn.list` - 查看所有出生点

### 协议系统
这些权限控制玩家协议系统：
- `kamihub.agreement.change` - 标记协议已更改（强制重新接受）

## 权限设置示例

### LuckPerms 配置
```bash
# 向管理员授予所有 KamiHub 权限
/lp group admin permission set kamihub.* true

# 向版主授予特定权限
/lp group moderator permission set kamihub.spawn.* true
/lp group moderator permission set kamihub.anti-break.bypass true

# 向玩家授予基本权限（通常是自动的）
/lp group default permission set kamihub.spawn true
/lp group default permission set kamihub.action-bar true
```

### PermissionsEx 配置
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

## 默认权限行为

### 自动授予（默认：true）
这些权限自动授予所有玩家：
- 基本显示功能（action-bar、boss-bar）
- 玩家保护功能（anti-damage、anti-hunger）
- 基本传送功能（spawn、void-tp）
- 广播通知

### 需要分配（默认：false）
这些权限必须显式授予：
- 管理命令（reload、update、help）
- 出生点管理（add、remove、set、list）
- 保护绕过（所有 anti-* bypass 权限）
- 协议管理

## 最佳实践

1. **使用基于组的权限**：将权限分配给组而不是个人玩家
2. **最小权限原则**：只授予必要的权限
3. **定期审核**：定期检查权限分配
4. **测试权限**：在测试环境中验证权限设置
5. **记录更改**：跟踪权限修改

---

*有关使用特定权限插件配置权限的更多信息，请查阅其各自的文档。*
