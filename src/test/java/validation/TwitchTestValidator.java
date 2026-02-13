package validation;

import org.testng.Assert;

public class TwitchTestValidator {

    public static void searchIcon(Boolean b) {
        Assert.assertTrue(b, "Search input did not open after clicking search icon");
    }
}
