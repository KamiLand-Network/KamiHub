# 开发指南

> KamiHub 插件开发文档

欢迎来到KamiHub开发文档！本指南将帮助您了解如何基于KamiHub进行二次开发、创建自定义模块以及集成第三方插件。

## 开发概述

### 开发环境要求
- **Java版本**: Java 21+
- **构建工具**: 推荐 Gradle 8.13+
- **IDE**: 推荐 IntelliJ IDEA
- **服务器**: Paper 1.21.4+

### 作为依赖
您可以将KamiHub作为依赖引用（`compileOnly`，仅在编译时可用，不包含在最终构建中）。

#### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.KamiLand-Network</groupId>
        <artifactId>KamiHub</artifactId>
        <version>VERSION</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

#### Gradle Kotlin
```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.KamiLand-Network:KamiHub:VERSION")
}
```

#### Gradle Groovy
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    compileOnly 'com.github.KamiLand-Network:KamiHub:VERSION'
}
```
