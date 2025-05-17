package Other.seanca3.intern;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GmailTesting {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.google.com/mail/u/0/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement createEmailButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Create account']")));
        createEmailButton.click();
        WebElement type = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()= 'For my personal use']")));
        type.click();
        WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='whsOnd zHQkBf']")));
        name.sendKeys("Paola");
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class= 'VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 BqKGqe Jskylb TrZEUc lw1w4b']")));
        nextButton.click();
        WebElement monthDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='month']")));
        Select selectMonth = new Select(monthDropdown);
        selectMonth.selectByVisibleText("December");

        WebElement date= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='whsOnd zHQkBf']")));
        date.sendKeys("30");
        WebElement year = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='year']")));
        year.sendKeys("2004");

        WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='gender']")));
        Select selectGender = new Select(gender);
        selectGender.selectByVisibleText("Female");

        WebElement next1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 BqKGqe Jskylb TrZEUc lw1w4b']")));
        next1.click();

//        try {
//            WebElement createYourOwn = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("//button//span[text()='Create your own Gmail address']")));
//            createYourOwn.click();
//            System.out.println("Suggestion UI shown and handled.");
//        } catch (TimeoutException e) {
//            System.out.println("No suggestion UI shown.");
//        }
        WebElement created = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='selectionc3']")));
        created.click();
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Username']")));
        emailField.sendKeys("paolaautomation2025");
    }
}

   