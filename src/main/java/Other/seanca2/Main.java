package Other.seanca2;

import jdk.jfr.Threshold;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qbek.github.io/selenium-exercises/en/check_boxes.html");
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
//        WebElement red = driver.findElement(By.name("red"));
//        red.click();
//        Thread.sleep(2000);
//        WebElement blue = driver.findElement(By.name("blue"));
//        blue.click();
//        Thread.sleep(2000);
//        WebElement result = driver.findElement(By.id("light"));
//        if(result.isDisplayed()) {
//            System.out.println("Correct!");
//        }
//        else {
//            System.out.println("Incorrect!");
//        }
//
//
//        List<WebElement> checkbox = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@id = 'switch']")));
//
//        for (int i = 0; i < checkbox.size(); i++) {
//            if (checkbox.get(i).isSelected()) {
//                System.out.println("Checkbox  is selected");
//            } else {
//                System.out.println("Checkbox is not selected");
//            }
//        }
//
//        driver.quit();

        WebElement red = driver.findElement(By.name("red"));
        red.click();
        Thread.sleep(2000);

        WebElement blue = driver.findElement(By.name("blue"));
        blue.click();
        Thread.sleep(2000);

        WebElement result = driver.findElement(By.id("light"));
        assert result.isDisplayed() : "Incorrect! The light is not displayed as expected.";

        List<WebElement> checkbox = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@id = 'switch']")));

        for (WebElement webElement : checkbox) {
            assert webElement.isSelected() : "Checkbox  is not selected.";
        }

        driver.quit();

    }
}
