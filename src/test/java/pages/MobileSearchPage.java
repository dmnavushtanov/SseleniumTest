package pages;

import Utils.UiTestsUrls;
import Utils.WebDriverUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
public class MobileSearchPage {
    private WebDriver driver;

    private final static String CHROME = "Chrome";
    private final static String FF = "FireFox";
    private final static String ACCEPT_COOKIE_BTN = "//button//p[@class='fc-button-label' and contains(text(), 'Към сайта')]";
    final String priceXpath = "//span[@class='price']";
    final String carModelXpath = "//table[@class='tablereset']//a[@class='mmm']";
    @FindBy(xpath = "//a[contains(text(), 'КОРЕКЦИЯ НА ТЪРСЕНЕТО')]")
    WebElement correctTheSearch;
    @FindBy(xpath = "//input[@type='button' and @value='Т Ъ Р С И']")
    WebElement searchBtn;
    @FindBy(xpath = "//select[@name='marka']")
    WebElement manufacturerSelect;
    @FindBy(xpath = "//input[@name='price1']")
    WebElement maxPriceInputField;

    public MobileSearchPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @Given("verify the landing page")
    public void verifyMobilePageOpened() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains(UiTestsUrls.MOBILE_BG_LAND_URL));
        ExpectedConditions.visibilityOf(searchBtn);
    }

    @Then("select car manufacturer {string} from drop down")
    public void selectCarManufacturer(String carManufacturer) {
        Select s = new Select(manufacturerSelect);
        s.selectByValue(carManufacturer);
        System.out.println(s.getFirstSelectedOption().getText());
        Assert.assertTrue(s.getFirstSelectedOption().getText().equals(carManufacturer));
    }

    @Then("put max price {string} for model")
    public void fillMaxPrice(String maxPrice) {
        ExpectedConditions.elementToBeClickable(maxPriceInputField);
        maxPriceInputField.sendKeys(maxPrice);
    }

    @Then("Click search btn")
    public void clickSearchBtn() throws InterruptedException {
        ExpectedConditions.elementToBeClickable(searchBtn);
        searchBtn.click();
        Thread.sleep(3000);
    }

    @Given("Navigate to mobile {string} and accept cookies if exist")
    public void navigateToPage(String url) throws InterruptedException {
        getDriver().get(url);
        Thread.sleep(3000);
        acceptCookiesIfExist(driver);
    }

    public void acceptCookiesIfExist(WebDriver driver) {
        try {
            driver.findElement(By.xpath(ACCEPT_COOKIE_BTN)).click();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("It seems the cookie pop up didn't show");
        }
    }

    @Then("Verify on the result page")
    public void verifyOnTheResultPage() {
        ExpectedConditions.visibilityOf(correctTheSearch);
        Assert.assertFalse(driver.getCurrentUrl().equals(UiTestsUrls.MOBILE_BG_LAND_URL));
    }

    public List<String> getAllPricesOnThePage() {
        return driver.findElements(By.xpath(priceXpath)).stream().map(WebElement::getText).toList();
    }

    //this method can be easily changed to compare all prices on the page
    @Then("Verify price {string} of the first car")
    public void verifyFirstCarIsLessThenPrice(String price) {
        List<String> allPrices = getAllPricesOnThePage();
        if (allPrices.size() != 0) {
            String priceToCompare = allPrices.get(0).replaceAll(" ", "").replaceAll("лв.", "");
//what we find is something like 3200lv so we need to extract only the digit
            Assert.assertTrue(Integer.parseInt(priceToCompare) < Integer.parseInt(price));
        } else {
            Assert.assertTrue(false, "There is no price field on the page");
        }
    }

    @Then("Verify car model {string}")
    public void verifyCarModel(String carModel) {
        List<String> allCarsModels = driver.findElements(By.xpath(carModelXpath)).stream().map(WebElement::getText).toList();
        if (allCarsModels.size() != 0) {
            Assert.assertTrue(allCarsModels.get(0).contains(carModel));
        } else {
            Assert.assertTrue(false, "There is no cars from this model on the page");
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = WebDriverUtils.initDriver(CHROME);
        }
        return driver;
    }

    @Then("close driver")
    public void quiteDriver() {
        getDriver().close();
    }

}
