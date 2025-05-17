package Other.seanca3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class TestBibloteka {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://www.bksh.al/");
        WebElement katalog = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='icon mb-4']")));
        katalog.click();
        WebElement titulliOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'indicatorContainer')])[2]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", titulliOption);
        titulliOption.click();
        Actions actions = new Actions(driver);
        WebElement secondDrop = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'indicatorContainer')])[3]")));
        actions.moveToElement(secondDrop).click().perform();
        WebElement permban = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'fillon me')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", permban);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", permban);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Vendos elementin e kërkimit']")));
        input.sendKeys("Ferri");
        WebElement gjuhaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Gjuha']/..//input")));
        gjuhaInput.sendKeys("Shqip");

        WebElement gjuhaShqip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'option') and text()='Shqip']")));
        gjuhaShqip.click();

        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and @class='form-check-input']")));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Kërko')]")));
        button.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.katalogu-materiale")));
        WebElement liber = driver.findElement(By.xpath("//div[contains(@class, 'katalogu-materiale') and .//h6[contains(text(), 'Ferri')]]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", liber);
        Thread.sleep(5000);
        assert liber.isDisplayed(): "Libri nuk u shfaq! ";
        WebElement autori = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Dante Alighieri')]")));
        autori.click();
        Thread.sleep(5000);
        WebElement button2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Rendit')]")));
        button2.click();
        Thread.sleep(5000);
        WebElement rendit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Datë botimi ↓')]")));
        rendit.click();
        Thread.sleep(5000);
        driver.quit();
    }
}