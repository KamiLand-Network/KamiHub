# API 文档

> KamiHub 开发者 API 参考

本文档提供KamiHub插件的API参考。

## API 概览

### 核心架构

KamiHub采用模块化架构设计，每个功能模块都提供独立的API接口：

```java
// 获取KamiHubAPI实例
KamiHubAPI api = KamiHubAPI.getInstance();
// 获取KamiHub插件实例
KamiHub plugin = api.getPlugin();
```

#### 事件API
```java
@EventHandler
public void onAgreementChange(AgreementChangeEvent event) {
    // 当协议被更改时
    // 当前版本的更改协议事实上需要您在配置文件中修改
    // 更改协议实质上只是重置玩家的协议状态
}

@EventHandler
public void onPlayerAgreement(PlayerAgreementEvent event) {
    Player player = event.getPlayer();
    boolean accepted = event.isAccepted();
}
```

> 该API正在完善中，更多内容敬请等待。
