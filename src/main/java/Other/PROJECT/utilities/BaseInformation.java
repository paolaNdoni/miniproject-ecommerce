package Other.PROJECT.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseInformation {
    public static BaseInformation getBaseInformation(){

        return new BaseInformation();
    }
    public static WebDriver driver;
    public static WebDriver getDriver(){
        if(driver == null){
            String browserType = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browserType){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }


    public static void quit() {
        driver.quit();
    }
}

