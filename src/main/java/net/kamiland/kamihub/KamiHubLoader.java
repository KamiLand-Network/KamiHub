package net.kamiland.kamihub;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;

public class KamiHubLoader implements PluginLoader {

    @Override
    public void classloader(PluginClasspathBuilder classpathBuilder) {
        MavenLibraryResolver resolver = new MavenLibraryResolver();
        resolver.addRepository(new RemoteRepository.Builder(
                "paper",
                "default",
                "https://repo.papermc.io/repository/maven-public/")
                .build());
        resolver.addRepository(new RemoteRepository.Builder(
                "jitpack",
                "default",
                "https://www.jitpack.io")
                .build());
        resolver.addRepository(new RemoteRepository.Builder(
                "codemc",
                "default",
                "https://repo.codemc.io/repository/maven-public/")
                .build());
        resolver.addRepository(new RemoteRepository.Builder(
                "panda",
                "default",
                "https://repo.panda-lang.org/releases")
                .build());

        resolver.addDependency(new Dependency(new DefaultArtifact("com.zaxxer:HikariCP:6.3.0"), null));
        resolver.addDependency(new Dependency(new DefaultArtifact("dev.rollczi:litecommands-bukkit:3.10.0"), null));
        resolver.addDependency(new Dependency(new DefaultArtifact("com.github.LeonMangler:SuperVanish:6.2.19"), null));
        resolver.addDependency(new Dependency(new DefaultArtifact("de.tr7zw:item-nbt-api-plugin:2.15.1"), null));
        resolver.addDependency(new Dependency(new DefaultArtifact("com.mysql:mysql-connector-j:9.3.0"), null));
        resolver.addDependency(new Dependency(new DefaultArtifact("com.h2database:h2:2.3.232"), null));
        resolver.addDependency(new Dependency(new DefaultArtifact("org.bstats:bstats-bukkit:3.0.2"), null));

        classpathBuilder.addLibrary(resolver);
    }

}
