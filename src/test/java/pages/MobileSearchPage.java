package pages;

import Utils.UiTestsUrls;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class MobileSearchPage {
    @FindBy(xpath = "//input[@type='button' and @value='Т Ъ Р С И']")
    WebElement searchBtn;
    @FindBy(xpath = "//select[@name='marka']")
    WebElement manufacturerSelect;
    @FindBy(xpath = "//input[@name='price1']")
    WebElement maxPriceInputField;

    private final WebDriver driver;

    public MobileSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Given("verify the landing page")
    public void verifyMobilePageOpened() {
        Assert.assertTrue(driver.getCurrentUrl().contains(UiTestsUrls.MOBILE_BG_LAND_URL));
        ExpectedConditions.visibilityOf(searchBtn);
    }

    @Then("select car manufacturer from drop down")
    public void selectCarManufacturer(String carManufacturer) {
        Select s = new Select(manufacturerSelect);
        s.selectByValue(carManufacturer);
        System.out.println(s.getFirstSelectedOption().getText());
        Assert.assertTrue(s.getFirstSelectedOption().getText().equals(carManufacturer));
    }

    @Then("put max price for model")
    public void fillMaxPrice(String maxPrice) {
        ExpectedConditions.elementToBeClickable(maxPriceInputField);
        maxPriceInputField.sendKeys(maxPrice);
    }

    public void clickSearchBtn() {
        ExpectedConditions.elementToBeClickable(searchBtn);
        searchBtn.click();
    }


}
