package tests;

import Utils.WebDriverUtils;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseTestHelper {
    private WebDriver driver;
    private final static String FF = "FireFox";
    private final static String CHROME = "Chrome";
    private final static String ACCEPT_COOKIE_BTN = "//button//p[@class='fc-button-label' and contains(text(), 'Към сайта')]";


    public WebDriver getDriver() {
        if (driver == null) {
            driver = WebDriverUtils.initDriver(CHROME);
        }
        return driver;
    }

    @Given("Navigate to mobile url and accept cookies if exist")
    public void navigateToPage(WebDriver driver, String url) {
        driver.get(url);
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
}
