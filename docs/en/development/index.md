# Development Guide

> KamiHub Plugin Development Documentation

Welcome to the KamiHub development documentation! This guide will help you understand how to perform secondary development based on KamiHub, create custom modules, and integrate third-party plugins.

## Development Overview

### Development Environment Requirements
- **Java Version**: Java 21+
- **Build Tool**: Recommended Gradle 8.13+
- **IDE**: Recommended IntelliJ IDEA
- **Server**: Paper 1.21.4+

### As a Dependency
You can reference KamiHub as a dependency (`compileOnly`, available only at compile time, not included in the final build).

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
