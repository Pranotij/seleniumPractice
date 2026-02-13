package utils;


import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;


public class FailureReportGenerator {
    private static final String REPORT_PATH = "reports/failure-report.txt";

    public static void generateReport(String testName, String reason, String screenshot) {

        try {
            Path reportFilePath = Path.of(REPORT_PATH);
            Files.createDirectories(reportFilePath.getParent());


            try (FileWriter writer = new FileWriter(REPORT_PATH, true)) {
                writer.write("Test Name: " + testName + "\n");
                writer.write("Time: " + LocalDateTime.now() + "\n");
                writer.write("Reason: " + reason + "\n");
                writer.write("Screenshot: " + screenshot + "\n");

                LogUtil.error("Failure report updated for: " + testName);
            }
        } catch (Exception e) {
            LogUtil.error("Failed to write failure Report: " + e.getMessage());
        }

    }
}