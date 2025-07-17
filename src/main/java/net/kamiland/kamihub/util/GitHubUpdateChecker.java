package net.kamiland.kamihub.util;

import com.google.gson.*;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.function.Predicate;

public class GitHubUpdateChecker {

    private final String apiUrl;
    private final String currentVersion;
    private final Predicate<GitHubRelease> filter;

    private static final Gson gson = new GsonBuilder().create();

    public GitHubUpdateChecker(String apiUrl, String currentVersion, Predicate<GitHubRelease> filter) {
        this.apiUrl = apiUrl;
        this.currentVersion = currentVersion;
        this.filter = filter;
    }

    public Optional<GitHubRelease> fetchLatestRelease(Logger logger) {
        GitHubVersion current = new GitHubVersion(currentVersion);
        boolean currentIsPre = current.isPreRelease();

        try {
            HttpResponse<String> response;
            try (HttpClient client = HttpClient.newHttpClient()) {
                HttpRequest request = HttpRequest.newBuilder(URI.create(apiUrl))
                        .header("Accept", "application/vnd.github+json")
                        .header("User-Agent", "GithubUpdateChecker")
                        .build();

                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            }
            JsonArray releases = JsonParser.parseString(response.body()).getAsJsonArray();

            for (JsonElement el : releases) {
                JsonObject obj = el.getAsJsonObject();
                String tag = obj.get("tag_name").getAsString();
                String name = obj.get("name").getAsString();
                String body = obj.has("body") ? obj.get("body").getAsString() : "";
                boolean prerelease = obj.get("prerelease").getAsBoolean();
                ZonedDateTime publishedAt = ZonedDateTime.parse(obj.get("published_at").getAsString());

                GitHubRelease release = new GitHubRelease(tag, name, body, publishedAt, prerelease);
                GitHubVersion remote = new GitHubVersion(tag);
                boolean remoteIsPre = remote.isPreRelease();

                if (!filter.test(release)) continue;

                boolean shouldUpdate = false;
                if (!remoteIsPre && !currentIsPre && remote.compareTo(current) > 0) {
                    shouldUpdate = true;
                } else if (!remoteIsPre && currentIsPre && remote.compareTo(current) >= 0) {
                    shouldUpdate = true;
                }

                if (shouldUpdate) {
                    return Optional.of(release);
                }
            }

        } catch (IOException | InterruptedException | JsonParseException e) {
            logger.warn("Failed to fetch latest release from GitHub", e);
        }

        return Optional.empty();
    }
}