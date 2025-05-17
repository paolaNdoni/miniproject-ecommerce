package Other.seanca2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SeleniumPracticeForm {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 1000);");  // Scroll down by 1000 pixels
            Thread.sleep(1000);  // Wait for content to load (adjust time as necessary)
        }        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        driver.get("https://www.techlistic.com/p/selenium-practice-form.html");


        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
        name.sendKeys("Paola");
        WebElement lastname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastname")));
        lastname.sendKeys("Ndoni");


        WebElement gender = wait.until(ExpectedConditions.elementToBeClickable(By.id("sex-1")));
        gender.click();

        WebElement years = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("exp-0")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", years);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", years);


        WebElement date = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("datepicker")));
        date.sendKeys("05/04/2025");

        WebElement profession = wait.until(ExpectedConditions.elementToBeClickable(By.id("profession-1")));
        profession.click();

        WebElement tool = wait.until(ExpectedConditions.elementToBeClickable(By.id("tool-2")));
        tool.click();
        Select continents = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("continents"))));
        continents.selectByVisibleText("Europe");

        Select commands = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selenium_commands"))));
        commands.selectByVisibleText("Browser Commands");
        commands.selectByVisibleText("Navigation Commands");

        WebElement upload = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("photo")));
        upload.sendKeys("C:\\Users\\User\\Downloads\\82711d69-1cfe-4869-99a8-d45c02a97bef.jfif");
        Thread.sleep(5000);

        WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Click here to Download File')]")));
        js.executeScript("arguments[0].scrollIntoView(true);", downloadLink);
        js.executeScript("arguments[0].click();", downloadLink);

        driver.navigate().back();
        String url = driver.getCurrentUrl();
        assert url != null: "Invalid URL";

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        submit.click();
        assert submit.isEnabled() : "Submit button is not enabled";
        driver.quit();
    }
}