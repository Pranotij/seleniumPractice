package utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FlakyTracker {
    private static final Map<String, Integer> flakyElements = new ConcurrentHashMap<>();

    public static void recordFlaky(String locator) {
        flakyElements.merge(locator, 1, Integer::sum);
        LogUtil.warn("Flaky element detected: " + locator);
    }

    public static Map<String, Integer> getFlakyElements() {
        return flakyElements;
    }

    public static void clear() {
        flakyElements.clear();
        LogUtil.info("Flaky Tracker Cleared");
    }
}
