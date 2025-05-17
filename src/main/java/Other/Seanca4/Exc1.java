package Other.Seanca4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Exc1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/slider/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions action = new Actions(driver);
        WebElement slide = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='range-slider range-slider--primary']")));
        action.clickAndHold(slide).moveByOffset(150, 0).release().perform();
        Thread.sleep(5000);
        assert slide.isDisplayed() : "The slide is not displayed";
        driver.quit();

    }
}
