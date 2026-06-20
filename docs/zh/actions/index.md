# 什么是Actions？

Actions是KamiHub中用于方便服务器管理员快速实现自定义功能的一种类指令功能。
它的格式看起来是这样的：
- `[player]say Hello`
- `[message]<green>You are so smart!`

> 看上去很简单，不是吗？

如你所见，每一个Actions通常包含两部分：**前缀** 和 **值**。
**前缀** 用于指定每个Actions的用途，**值** 根据不同的前缀，有不同的意义和作用。
对于一个`[player]say Hello`，它的前缀是`[player]`，值是`say Hello`。

## 官方Actions

为了方便管理员实现自定义功能，KamiHub目前提供了以下几个Actions：
- `[player]`：以玩家身份执行指令，此时的值为指令内容
- `[console]`：以控制台身份执行指令，此时的值为指令内容
- `[message]`：向玩家发送消息，此时的值为消息内容
- `[broadcast]`：向玩家广播消息，此时的值为消息内容

对于如`[message]`，`[broadcast]`这样的行为，它们用于向玩家发送消息，所以它们的值都是MiniMessage格式的，并且支持PAPI占位符解析。

## Actions的运用

目前，KamiHub仅在玩家协议模块，玩家同意或拒绝协议后执行Actions。
在未来的版本中，KamiHub会逐步支持更多的Actions，并开放API供开发者注册自定义Actions。

## 一些误区

习惯了写Yaml配置文件的用户，经常在冒号后加空格以确保格式正确。
`key: value`
但是Actions的前缀后是不应该出现空格的，除非您希望在消息文本的开头加入空格。
`[action]value`

如果您使用 `[message] Im a message!` ，那么玩家实际上收到的消息是：` Im a message!` （前面有一个空格）
