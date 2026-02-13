package tests;

import actions.ActionFunctions;
import actions.ElementActions;
import actions.WaitActions;
import base.BaseTest;
import locators.HomePageLocator;
import locators.ResultStreamsPageLocator;
import locators.SearchPageLocator;
import org.testng.annotations.Test;
import utils.GifRecorder;
import utils.ScreenshotUtil;
import validation.TwitchTestValidator;

public class TwitchTest extends BaseTest {

    @Test
    public void verifyUserCanOpenStreamFromSearch() {
        HomePageLocator homePageLocator = new HomePageLocator();
        SearchPageLocator searchPage = new SearchPageLocator();
        ResultStreamsPageLocator resultStreamsPage = new ResultStreamsPageLocator();
        ActionFunctions functions = new ActionFunctions(driver);

        ElementActions elementActions = new ElementActions(driver);
        WaitActions waitActions = new WaitActions(driver);


        GifRecorder.captureFrame(driver);
        boolean clickOnSearchPage = elementActions.click(homePageLocator.bottomNavSearchElement);
        GifRecorder.captureFrame(driver);//for GIF capturing
        TwitchTestValidator.searchIcon(clickOnSearchPage);

        elementActions.type(searchPage.searchIconElement, "StarCraft II");
        GifRecorder.captureFrame(driver);//for GIF capturing

        functions.selectFirstResultIfExists(driver);
        GifRecorder.captureFrame(driver);//for GIF capturing

        elementActions.scrollDownTwice();
        GifRecorder.captureFrame(driver);//for GIF capturing

        functions.openFirstStream();
        GifRecorder.captureFrame(driver);//for GIF capturing


        waitActions.waitForVideoToPlay();
        GifRecorder.captureFrame(driver);//for GIF capturing


        ScreenshotUtil.takeScreenShot(driver, "OutputNRequiredSS");

    }


}
