package net.kamiland.kamihub.util;

import java.time.ZonedDateTime;

public record GitHubRelease(String tag, String name, String body, ZonedDateTime publishedAt, boolean prerelease) {

    @Override
    public String toString() {
        return "Release[" + tag + (prerelease ? " (pre)" : "") + "]";
    }

    public boolean isStable() {
        return !prerelease;
    }

}

