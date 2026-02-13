package actions;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.FlakyTracker;
import utils.LogUtil;

public class ElementActions {

    WebDriver driver;
    WaitActions wait;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitActions(driver);
    }

    public boolean click(By locator) {
        boolean b = false;
        try {
            wait.waitForClickable(locator).click();
            b = true;
        } catch (Exception firstFailure) {
            LogUtil.warn("First click attempt failed for: " + locator);
            try {
                wait.waitForClickable(locator).click();
                b = true;
                FlakyTracker.recordFlaky(locator.toString());
            } catch (Exception retryFailure) {
                LogUtil.error("Click failed after retry: " + locator);
                throw retryFailure;
            }

        }

        return b;
    }

    public void type(By locator, String text) {
        try {
            WebElement element = wait.waitForVisbilty(locator);
            element.click();
            element.clear();
            element.sendKeys(text);
        } catch (Exception firstFailure) {
            LogUtil.warn("First click attempt failed for: " + locator);
            try {
                WebElement element = wait.waitForVisbilty(locator);
                element.click();
                element.clear();
                element.sendKeys(text);

                FlakyTracker.recordFlaky(locator.toString());
            } catch (Exception retryFailure) {
                LogUtil.error("Click failed after retry: " + locator);
                throw retryFailure;
            }
        }
    }

    @SneakyThrows
    public void scrollDownTwice() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 2; i++) {
            js.executeScript("window.scrollBy(0,window.innerHeight);");
            Thread.sleep(1000);
        }
        //scroll to top again
        js.executeScript("window.scrollTo({top:0,behavior: 'smooth'});");

    }

}
