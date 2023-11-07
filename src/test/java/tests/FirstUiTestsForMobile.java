package tests;

import Utils.UiTestsUrls;
import org.testng.annotations.Test;
import pages.MobileSearchPage;


public class FirstUiTestsForMobile extends BaseTestHelper {


    @Test(description = "Navigate to Mobile select model and price for a car")
    void firstTest() {
        navigateToPage(getDriver(), UiTestsUrls.MOBILE_BG_LAND_URL);
        MobileSearchPage page = new MobileSearchPage(getDriver());
        page.verifyMobilePageOpened();
        page.selectCarManufacturer("Audi");
        page.fillMaxPrice("5000");
        page.clickSearchBtn();

    }

}
