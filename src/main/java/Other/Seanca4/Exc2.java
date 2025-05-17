package Other.Seanca4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Exc2 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement name = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userName")));
        name.sendKeys("Paola");
        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("test@gmail.com");
        WebElement address = driver.findElement(By.id("currentAddress"));
        address.sendKeys("123 Main St");
        WebElement permanentAddressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("permanentAddress")));
        String currentAddress = address.getAttribute("value");
        permanentAddressField.sendKeys(currentAddress);

    }
}
