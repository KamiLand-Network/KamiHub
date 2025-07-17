import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    id("java")
    id("de.eldoria.plugin-yml.paper") version "0.7.1"
    id("com.gradleup.shadow") version "8.3.7"
}

group = "net.kamiland"
version = "1.0-alpha"
description = "An efficient and modern integrated server security management plugin."
java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

repositories {
    mavenCentral()
    maven("https://www.jitpack.io")
    // PaperMC
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.papermc.io/repository/maven-snapshots/")
    maven("https://repo.papermc.io/repository/maven-releases/")
    // Sonatype
    maven("https://oss.sonatype.org/content/groups/public/")

    // Panda (LiteCommands)
    // maven("https://repo.panda-lang.org/releases")
    maven("https://repo.eternalcode.pl/#/releases/dev/rollczi/litecommands-bukkit")
    // Item-NBT-API
    maven("https://repo.codemc.io/repository/maven-public/")

    // PlaceholderAPI
    maven("https://repo.extendedclip.com/releases")
}

dependencies {
    compileOnly("org.jetbrains:annotations:26.0.2")
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")

    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")

    compileOnly("com.zaxxer:HikariCP:6.3.0")
    compileOnly("dev.rollczi:litecommands-bukkit:3.10.0")
    compileOnly("com.github.LeonMangler:SuperVanish:6.2.19")
    compileOnly("de.tr7zw:item-nbt-api-plugin:2.15.1")
    compileOnly("com.mysql:mysql-connector-j:9.3.0")
    compileOnly("com.h2database:h2:2.3.232")
    compileOnly("org.bstats:bstats-bukkit:3.0.2")

    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("net.luckperms:api:5.4")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7") {
        exclude("org.bukkit", "bukkit")
    }
}

paper {
    main = "net.kamiland.kamihub.KamiHub"

    bootstrapper = "net.kamiland.kamihub.KamiHubBootstrapper"
    loader = "net.kamiland.kamihub.KamiHubLoader"
    hasOpenClassloader = false

    generateLibrariesJson = false

    // foliaSupported = true

    apiVersion = "1.21.4"

    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
    authors = listOf("KamiLand", "while1cry", "SuXuan_Dev")

    prefix = "KamiHub"

    serverDependencies {
        register("PlaceholderAPI") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
        register("LuckPerms") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
        register("Vault") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
    }

    permissions {
        register("kamihub.*") {
            children = listOf(
                "kamihub.action-bar", "kamihub.anti-break.bypass", "kamihub.anti-interact.bypass",
                "kamihub.anti-place.bypass", "kamihub.anti-drop.bypass", "kamihub.anti-pickup.bypass",
                "kamihub.anti-hunger", "kamihub.anti-damage", "kamihub.anti-projectile.bypass",
                "kamihub.anti-attack.bypass", "kamihub.boss-bar", "kamihub.broadcast.notify",
                "kamihub.clear-chat.bypass", "kamihub.inventory.bypass", "kamihub.potion-effect",
                "kamihub.spawn", "kamihub.spawn.add", "kamihub.spawn.set",
                "kamihub.spawn.remove", "kamihub.spawn.list", "kamihub.void-tp",
                "kamihub.help", "kamihub.reload", "kamihub.module.list")
        }
        register("kamihub.action-bar") {
            default = BukkitPluginDescription.Permission.Default.TRUE
        }
        register("kamihub.anti-break.bypass") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.anti-interact.bypass") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.anti-place.bypass") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.anti-drop.bypass") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.anti-pickup.bypass") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.anti-hunger") {
            default = BukkitPluginDescription.Permission.Default.TRUE
        }
        register("kamihub.anti-damage") {
            default = BukkitPluginDescription.Permission.Default.TRUE
        }
        register("kamihub.anti-projectile.bypass") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.anti-attack.bypass") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.boss-bar") {
            default = BukkitPluginDescription.Permission.Default.TRUE
        }
        register("kamihub.broadcast.notify") {
            default = BukkitPluginDescription.Permission.Default.TRUE
        }
        register("kamihub.clear-chat.bypass") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.inventory.bypass") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.potion-effect") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.spawn") {
            default = BukkitPluginDescription.Permission.Default.TRUE
        }
        register("kamihub.spawn.add") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.spawn.set") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.spawn.remove") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.spawn.list") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.void-tp") {
            default = BukkitPluginDescription.Permission.Default.TRUE
        }
        register("kamihub.help") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.reload") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.module.list") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("kamihub.update") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
    }
}

val copyShadowJar by tasks.registering(Copy::class) {
    from(tasks.named("shadowJar"))
    into("/server/plugins")

    dependsOn("shadowJar")

    doFirst {
        file("/server/plugins").mkdirs()
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    archiveBaseName.set("KamiHub")
    archiveClassifier.set("")

    mergeServiceFiles()
}

tasks.compileJava {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
