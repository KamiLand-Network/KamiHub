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
    apiVersion = "1.13"
    description = "${project.description}"
    authors = listOf("KamiLand", "while1cry")
    website = "https://www.kamiland.net/plugins/ultimatehub"
    softDepend = listOf("PlaceholderAPI")

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

    // PlaceholderAPI
    maven("https://repo.extendedclip.com/releases")
}

dependencies {
    compileOnly("org.jetbrains:annotations:26.0.2")
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")

    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    implementation("dev.rollczi:litecommands-bukkit:3.10.0")
    implementation("xyz.xenondevs.invui:invui:1.46")

    implementation("com.github.LeonMangler:SuperVanish:6.2.19")
    compileOnly("me.clip:placeholderapi:2.11.6")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    archiveBaseName.set("UltimateHub")
    archiveClassifier.set("${project.version}")

    relocate("dev.rollczi", "net.kamiland.ultimatehub.lib.rollczi")
    relocate("xyz.xenondevs", "net.kamiland.ultimatehub.lib.xenondevs")

    relocate("dev.myzelyam", "net.kamiland.ultimatehub.lib.myzelyam")

    mergeServiceFiles()
    minimize()
}

tasks.compileJava {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}