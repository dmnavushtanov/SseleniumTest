package pages;

import Utils.UiTestsUrls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class MobileResultPage {
    private final WebDriver driver;
    final String priceXpath = "//span[@class='price']";
    final String carModelXpath = "//a[@class='mmm']";

    @FindBy(xpath = "//a[contains(text(), 'КОРЕКЦИЯ НА ТЪРСЕНЕТО')]")
    WebElement correctTheSearch;

    public MobileResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyOnTheResultPage() {
        Assert.assertFalse(driver.getCurrentUrl().equals(UiTestsUrls.MOBILE_BG_LAND_URL));
        ExpectedConditions.visibilityOf(correctTheSearch);
    }

    public List<String> getAllPricesOnThePage() {
        return driver.findElements(By.xpath(priceXpath)).stream().map(WebElement::getText).toList();
    }

    public void verifyFirstCarIsLessThenPrice(String price) {
        List<String> allPrices = getAllPricesOnThePage();
        if (allPrices.size() != 0) {
            Assert.assertTrue(Integer.parseInt(allPrices.get(0)) < Integer.parseInt(price));
        } else {
            Assert.assertTrue(false, "There is no price field on the page");
        }
    }


}
