package Other.seanca3.intern;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdrionTesting {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();

        driver.get("https://www.neptun.al/");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement newIn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='newIn']")));
        newIn.click();
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
//        WebElement philipsSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//span[contains(text(), 'PRODUKTE TE REJA PHILIPS')]")));
//
//// Scroll the element into view using JavascriptExecutor
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView(true);", philipsSpan);
//
//// Wait for the link to be clickable after scrolling
//        WebElement parentLink = philipsSpan.findElement(By.xpath("./ancestor::a"));
//        wait.until(ExpectedConditions.elementToBeClickable(parentLink));
//
//// Click the link
//        parentLink.click();

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Kërko']")));

// Send the desired search term
        searchInput.sendKeys("Laptop");

// Optionally, simulate pressing the "Enter" key to trigger the search
        searchInput.sendKeys(Keys.ENTER);
    }
}
