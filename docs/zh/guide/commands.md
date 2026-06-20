# 命令参考

> KamiHub 完整命令列表

本页面提供KamiHub插件的所有命令详细说明，包括语法、参数、权限要求和使用示例。

## 命令概览

### 命令分类
- **核心命令**：插件管理和基础功能
- **出生传送**：传送点管理和传送功能
- **玩家协议**：协议管理和签署功能

### 命令格式说明
```
<必需参数>     # 必须提供的参数
[可选参数]     # 可选的参数
{选择参数}     # 从多个选项中选择一个
...           # 可以有多个相同类型的参数
```

## 核心命令

### 主命令 `/kamihub`
> 别名：`/khub`，`/kh`

#### 基础信息
```bash
# 显示插件信息和版本
/kamihub info

# 通过GitHub检查更新
# 需要权限：kamihub.update
/kamihub update

# 显示帮助信息
# 需要权限：kamihub.help
/kamihub help [页数]
```

#### 插件管理
```bash
# 重载配置
# 需要权限：kamihub.reload
/kamihub reload

# 列出所有模块及其启用状态
# 需要权限：kamihub.module.list
/kamihub modules list
```

#### 玩家协议
```bash
# 同意协议
/agreement accept

# 拒绝协议
/agreement reject

# 更改协议
# 需要权限：kamihub.agreement.change
/agreement change
```

#### 出生点传送命令
```bash
# 传送到出生点
# 需要权限：kamihub.spawn
/spawn

# 添加出生点
# 需要权限：kamihub.spawn.add
/spawn add <name>

# 移除出生点
# 需要权限：kamihub.spawn.remove
/spawn remove <name>

# 设置出生点
# 需要权限：kamihub.spawn.set
/spawn set <name>

# 列出所有出生点
# 需要权限：kamihub.spawn.list
/spawn list
```
