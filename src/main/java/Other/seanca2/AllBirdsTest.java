package Other.seanca2;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class AllBirdsTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.allbirds.com/");

        WebElement shopMen = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), 'Men')]")));
        shopMen.click();

        WebElement shoeType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[class='jsx-1630049278 Label Link__label']")));
        shoeType.click();

        List<WebElement> bestFor = driver.findElements(By.xpath("//legend[text()='Best For']/following-sibling::div//input"));
        if (!bestFor.isEmpty()) {
            bestFor.get(0).click();
            System.out.println("Best For selected: " + bestFor.get(0).isSelected());
        }

        List<WebElement> material = driver.findElements(By.xpath("//legend[text()='Material']/following-sibling::div//input"));
        if (!material.isEmpty()) {
            material.get(0).click();
            System.out.println("Material selected: " + material.get(0).isSelected());
        }

        WebElement hueOption = driver.findElement(By.xpath("//legend[text()='Hue']/following-sibling::div//button[1]"));
        hueOption.click();

        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-testid='ProductTileLink']")));
        firstProduct.click();

        WebElement color = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid^='ColorSwatch']")));
        color.click();

        WebElement size = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'10')]")));
        size.click();

        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add to cart')]")));
        addToCart.click();

        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Congrats! You get free standard shipping')]")));
        if (confirmation.isDisplayed()) {
            System.out.println("Success message displayed.");
        } else {
            System.out.println("Message not displayed.");
        }

        driver.quit();
    }
}
