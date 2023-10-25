package id.avecraft.songoda.core;

public class SongodaCoreConstants {
    private SongodaCoreConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static String getCoreVersion() {
        return "UNKNOWN_VESION";
    }

    public static String getProjectName() {
        return "SongodaCore";
    }

    public static String getGitHubProjectUrl() {
        return "https://github.com/craftaro/SongodaCore";
    }
}
