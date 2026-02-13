package utils;


import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final String FAILED_PATH = "screenshots/failed/";
    private static final String FLAKY_PATH = "screenshots/flaky/";

    @SneakyThrows
    public static void takeScreenShot(WebDriver driver, String folderName) {
        String folderPath = folderName;

        Path path = Paths.get(folderPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        String filePath = folderPath + File.separator + timestamp() + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(filePath));

        LogUtil.info("ScreenShot Saved at: " + filePath);

    }

    public static void captureFlakyScreenshot(WebDriver driver, String testName) {
        String fileName = testName + "_" + timestamp() + ".png";
        captureScreenshot(driver, FLAKY_PATH + fileName, testName);
        LogUtil.info("Falky screenshot saved: " + fileName);
    }

    public static void captureFailureScreenshot(WebDriver driver, String testName) {
        String fileName = testName + "_" + timestamp() + ".png";
        captureScreenshot(driver, FAILED_PATH + fileName, testName);
        LogUtil.info("Failed screenshot saved: " + fileName);
    }

    public static String captureScreenshot(WebDriver driver, String folderPath, String name) {

        try {

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Path filePath = Path.of(folderPath + name + File.separator + timestamp() + ".png");
            Files.createDirectories(filePath.getParent());
            Files.copy(src.toPath(), filePath);

            return filePath.toString();
        } catch (Exception e) {
            LogUtil.error("Screenshot captured failed  ", e);
            return "Screenshot capture failed";
        }
    }

    private static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    }
}
