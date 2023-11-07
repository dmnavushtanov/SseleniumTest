package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverUtils {

    public static WebDriver initDriver(String driverType) {
        WebDriver driver = null;
        if (driverType.equals("FireFox")) {
            driver = new FirefoxDriver();
        } else if (driverType.equals("Chrome")) {
            driver = new ChromeDriver();
        } else {
            throw new RuntimeException("You spceified unsupported browser version");
        }
        return driver;
    }
}
