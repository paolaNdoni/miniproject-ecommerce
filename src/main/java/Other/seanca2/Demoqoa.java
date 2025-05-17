package Other.seanca2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;



import java.time.Duration;
import java.util.List;

public class Demoqoa {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://demoqa.com/select-menu");

        WebElement groupDropdown = driver.findElement(By.id("withOptGroup"));
        groupDropdown.click();
        Thread.sleep(2000);
        WebElement firstGroupOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='css-1uccc91-singleValue']")));
        firstGroupOption.click();

        WebElement titleDropdown = driver.findElement(By.id("selectOne"));
        titleDropdown.click();
        WebElement firstTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(" div[class =' css-1uccc91-singleValue']")));
        System.out.println("First title option: " + firstTitle.getText());

        firstTitle.click();

        Select oldStyleDropdown = new Select(driver.findElement(By.id("oldSelectMenu")));
        List<WebElement> oldOptions = oldStyleDropdown.getOptions();
        System.out.println("Old-style options:");
        for (WebElement opt : oldOptions) {
            System.out.println(opt.getText());
        }

        oldStyleDropdown.selectByVisibleText("Green");
        System.out.println("Selected old-style option: Green");

        WebElement multiSelect = driver.findElement(By.xpath("//div[@class='  css-2b097c-container']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", multiSelect);
        WebElement multiColor = driver.findElement(By.xpath("//div[@id=' css-1wa3eu0-placeholder']"));
        multiColor.click();

        WebElement color1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Red']")));
        color1.click();
        WebElement color2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Blue']")));
        color2.click();
        WebElement color3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Green']")));
        color3.click();

        List<WebElement> selectedColors = driver.findElements(By.cssSelector(".css-yk16xz-control"));
        System.out.println("Selected colors:");
        for (WebElement c : selectedColors) {
            System.out.println( c.getText());
        }

        List<WebElement> removeButtons = driver.findElements(By.cssSelector(".css-19bqh2r"));
        for (WebElement remove : removeButtons) {
            remove.click();
        }
        System.out.println("Deselected all colors.");

        Select carDropdown = new Select(driver.findElement(By.id("cars")));
        carDropdown.selectByVisibleText("Volvo");

        WebElement selectedCar = carDropdown.getFirstSelectedOption();
        System.out.println("Selected car model: " + selectedCar.getText());

        driver.quit();
    }
}

