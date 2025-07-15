import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")
    id("de.eldoria.plugin-yml.bukkit") version "0.7.1"
    id("com.gradleup.shadow") version "8.3.7"
}

group = "net.kamiland"
version = "dev-0.1"
description = "An efficient and modern integrated server security management plugin."
java.targetCompatibility = JavaVersion.VERSION_17

bukkit {
    name = "UltimateHub"
    main = "net.kamiland.ultimatehub.UltimateHub"
    version = "${project.version}"
    apiVersion = "1.18"
    description = "${project.description}"
    authors = listOf("KamiLand", "while1cry", "SuXuan_Dev")
    website = "https://www.kamiland.net/plugins/ultimatehub"
    softDepend = listOf("PlaceholderAPI", "LuckPerms", "Vault")

    libraries = listOf(
        "com.h2database:h2:2.3.232",
        "com.zaxxer:HikariCP:6.3.0"
    )

    foliaSupported = true

    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
}

repositories {
    mavenCentral()
    maven("https://www.jitpack.io")
    // PaperMC
    maven("https://repo.papermc.io/repository/maven-public")

    // Panda (LiteCommands)
    maven("https://repo.panda-lang.org/releases")
    // InvUI
    maven("https://repo.xenondevs.xyz/releases")
    // Item-NBT-API
    maven("https://repo.codemc.io/repository/maven-public/")

    // PlaceholderAPI
    maven("https://repo.extendedclip.com/releases")
}

dependencies {
    compileOnly("org.jetbrains:annotations:26.0.2")
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")

    compileOnly("com.zaxxer:HikariCP:6.3.0")

    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    implementation("dev.rollczi:litecommands-bukkit:3.10.0")
    implementation("xyz.xenondevs.invui:invui:1.46")

    implementation("com.github.LeonMangler:SuperVanish:6.2.19") {
        exclude("com.mojang", "brigadier")
    }
    implementation("de.tr7zw:item-nbt-api-plugin:2.15.1")
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("net.luckperms:api:5.4")

    compileOnly("com.mysql:mysql-connector-j:9.3.0")
    compileOnly("com.h2database:h2:2.3.232")
}

val copyShadowJar by tasks.registering(Copy::class) {
    from(tasks.named("shadowJar"))
    into("/run/plugins")

    dependsOn("shadowJar")

    doFirst {
        file("/run/plugins").mkdirs()
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    archiveBaseName.set("UltimateHub")
    archiveClassifier.set("")

    relocate("de.myzelyam", "net.kamiland.ultimatehub.lib.myzelyam")
    relocate("de.tr7zw", "net.kamiland.ultimatehub.lib.tr7zw")
    relocate("org.jetbrains", "net.kamiland.ultimatehub.lib.jetbrains")
    relocate("org.intellij", "net.kamiland.ultimatehub.lib.intellij")
    relocate("dev.rollczi", "net.kamiland.ultimatehub.lib.rollczi")
    relocate("xyz.xenondevs", "net.kamiland.ultimatehub.lib.xenondevs")

    mergeServiceFiles()
}

tasks.compileJava {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
