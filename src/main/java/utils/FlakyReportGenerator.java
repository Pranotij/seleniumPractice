package utils;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Map;

public class FlakyReportGenerator {
    private static final String REPORT_PATH = "reports/flaky-report.txt";

    public static void generateReport() {
        Map<String, Integer> flakyElements = FlakyTracker.getFlakyElements();

        if (flakyElements.isEmpty()) {
            LogUtil.info("No flaky element detected, report Not generated");
            return;
        }
        try {

            File folder = new File("reports");
            if (!folder.exists()) {
                folder.mkdir();
            }

            FileWriter writer = new FileWriter(REPORT_PATH);
            writer.write("Flaky Element Report \n");
            writer.write("Generated At: " + LocalDateTime.now() + "\n\n");

            for (Map.Entry<String, Integer> entry : flakyElements.entrySet()) {
                writer.write(entry.getKey() + "->" + entry.getValue() + "\n");
            }
            writer.close();
            LogUtil.info("Flaky report generated at : " + REPORT_PATH);
        } catch (Exception e) {
            LogUtil.error("Failed to generate Flaky Report " + e.getMessage());
        }
    }
}
