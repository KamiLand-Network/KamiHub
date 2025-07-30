package net.kamiland.kamihub.config;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Version implements Comparable<Version> {

    private int major;
    private int minor;
    private int patch;

    public Version(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public Version(String version) {
        String[] parts = version.split("\\.");
        if (parts.length == 3) {
            this.major = Integer.parseInt(parts[0]);
            this.minor = Integer.parseInt(parts[1]);
            this.patch = Integer.parseInt(parts[2]);
        } else if (parts.length == 2) {
            this.major = Integer.parseInt(parts[0]);
            this.minor = Integer.parseInt(parts[1]);
            this.patch = 0;
        } else if (parts.length == 1) {
            this.major = Integer.parseInt(parts[0]);
            this.minor = 0;
            this.patch = 0;
        } else {
            throw new IllegalArgumentException("Invalid version format: " + version);
        }
    }

    @Override
    public int compareTo(@NotNull Version other) {
        if (this.major != other.major)
            return this.major - other.major;
        if (this.minor != other.minor)
            return this.minor - other.minor;
        return this.patch - other.patch;
    }

}
