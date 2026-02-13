package base;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import utils.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("implicit.wait", 10)));
        driver.get(URLBuilder.getProdBaseUrl());
        // START GIF RECORDING
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        GifRecorder.start("reportsGif/test-run_" + timestamp + ".gif");
    }

    @AfterMethod
    public void tearDown() {
        // STOP GIF RECORDING
        GifRecorder.stop();
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void generateFlakyReport() {
        FlakyReportGenerator.generateReport();
        FlakyTracker.clear();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
