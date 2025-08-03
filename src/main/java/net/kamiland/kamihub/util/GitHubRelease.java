package net.kamiland.kamihub.util;

import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

public record GitHubRelease(String tag, String name, String body, ZonedDateTime publishedAt, boolean prerelease) {

    @NotNull
    @Override
    public String toString() {
        return "Release[" + tag + (prerelease ? " (pre)" : "") + "]";
    }

    public boolean isStable() {
        return !prerelease;
    }

}

