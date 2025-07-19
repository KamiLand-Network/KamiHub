package net.kamiland.kamihub.util;

import java.util.Objects;

public class GitHubVersion implements Comparable<GitHubVersion> {

    private final String raw;
    private final int major, minor, patch;
    private final String prerelease;

    public GitHubVersion(String version) {
        this.raw = version;

        String clean = version.startsWith("v") ? version.substring(1) : version;
        String[] parts = clean.split("-", 2);
        String[] numbers = parts[0].split("\\.");

        this.major = numbers.length > 0 ? parseInt(numbers[0]) : 0;
        this.minor = numbers.length > 1 ? parseInt(numbers[1]) : 0;
        this.patch = numbers.length > 2 ? parseInt(numbers[2]) : 0;
        this.prerelease = parts.length > 1 ? parts[1] : null;
    }

    private int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String raw() {
        return raw;
    }

    public boolean isPreRelease() {
        return prerelease != null;
    }

    @Override
    public int compareTo(GitHubVersion o) {
        if (major != o.major) return Integer.compare(major, o.major);
        if (minor != o.minor) return Integer.compare(minor, o.minor);
        if (patch != o.patch) return Integer.compare(patch, o.patch);

        if (Objects.equals(prerelease, o.prerelease)) return 0;
        if (prerelease == null) return 1;
        if (o.prerelease == null) return -1;
        return prerelease.compareTo(o.prerelease);
    }

    @Override
    public String toString() {
        return raw;
    }

}