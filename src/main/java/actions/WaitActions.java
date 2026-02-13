package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LogUtil;

import java.time.Duration;

public class WaitActions {

    WebDriver driver;
    WebDriverWait wait;

    public WaitActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    }

    public WebElement waitForClickable(By locator) {
        LogUtil.info("Waiting for Visibilty: " + locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForVisbilty(By locator) {
        LogUtil.info("Waiting for Visibilty: " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForVideoToPlay() {
        LogUtil.info("Waiting for All Element to load");
        String script = "var v = document.querySelector('video');" + "return v && !v.paused && v.readyState >2;";

        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return (Boolean) ((JavascriptExecutor) d).executeScript(script);
            }
        });
    }

}
