package actions;

import locators.ResultStreamsPageLocator;
import locators.SearchPageLocator;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ActionFunctions {


    private final WebDriverWait wait;
    private final SearchPageLocator searchPageLocator;
    private final ResultStreamsPageLocator resultStreamsPageLocator;


    public ActionFunctions(WebDriver driver) {

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        this.searchPageLocator = new SearchPageLocator();
        this.resultStreamsPageLocator = new ResultStreamsPageLocator();
    }

    @SneakyThrows
    public void selectFirstResultIfExists(WebDriver driver) {
        Thread.sleep(5000);

        List<WebElement> results = driver.findElements(searchPageLocator.searchElementList);

        for (WebElement li : results) {
            if (li.isDisplayed() && !li.getText().trim().isEmpty()) {
                li.click();
                return;
            }
        }
        throw new RuntimeException("No Search result found");
    }

    @SneakyThrows
    public void openFirstStream() {

        WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(resultStreamsPageLocator.resultDivElement));
        WebElement firstItem = list.findElement(resultStreamsPageLocator.firstStreamElement);
        wait.until(ExpectedConditions.elementToBeClickable(firstItem));
        firstItem.click();

    }
}
