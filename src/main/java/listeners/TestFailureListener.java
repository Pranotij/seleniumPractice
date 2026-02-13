package listeners;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.FailureReportGenerator;
import utils.ScreenshotUtil;

public class TestFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        String testName = result.getName();
        String reason = result.getThrowable().toString();
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "failure", testName);

        FailureReportGenerator.generateReport(testName, reason, screenshotPath);
    }


}
