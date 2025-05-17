package Other.seanca1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CloseAndQuitDifference {
    public static void main(String[] args) throws Throwable {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://www.google.com";
        driver.get(baseUrl);
        WebElement newBrowserWindowsButton = driver.findElement(By.xpath("//div[@id='SIvCob']"));
        newBrowserWindowsButton.click();
        Thread.sleep(4000);
        driver.close();
        Thread.sleep(4000);
        driver.quit();
    }
}

