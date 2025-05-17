package InternshipProject.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseInformation {
    private static WebDriver driver;
    private BaseInformation() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserType = ConfigurationReader.get("browser").toLowerCase();
            switch (browserType) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();  // Auto setup
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Unsupported browser: " + browserType);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }
    public static void quit () {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }
}


