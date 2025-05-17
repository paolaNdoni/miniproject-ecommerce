package MiniProject.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {
    private BaseInformation baseInformation;
    private Duration defaultDuration;
    private WebDriver driver = BaseInformation.getDriver();

    public WaitUtils(BaseInformation baseInformation , Duration defaultDuration) {
        this.baseInformation = baseInformation;
        this.defaultDuration = defaultDuration;

    }
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            System.out.println("ERROR in waitForMethod");
        }
    }
    public WebElement waitForElementVisibleWithCustomTime(long mills, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(mills));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public WebElement waitForElementVisibleWithCustomTime(long seconds, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public WebElement waitForElementVisible(WebElement element) {
        return waitForElementVisibleWithCustomTime(defaultDuration.toSeconds(), element);
    }
    public WebElement waitForElementVisible(By locator) {
        return waitForElementVisibleWithCustomTime(defaultDuration.toSeconds(), locator);
    }

    public WebElement waitForElementClickableWithCustomTime(long seconds, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementClickable(WebElement element) {
        return waitForElementClickableWithCustomTime(defaultDuration.toSeconds(),element);
    }



    public void waitForAttributePresentWithCustomWaitTime(long mills, WebElement element,
                                                          String nameOfAttribute) {
        int milsWaitStep = 500;
        long numberOfLoops = mills / milsWaitStep;
        for (int i = 0; i < numberOfLoops; i++) {
            try {
                element.getAttribute(nameOfAttribute);
            } catch (Exception ex) {
                waitFor(milsWaitStep);
            }
        }
    }
    public void waitForAttributePresent(WebElement element, String nameOfAttribute) {
        waitForAttributePresentWithCustomWaitTime(defaultDuration.toMillis(), element, nameOfAttribute);
    }

    public List<WebElement> waitForAllElementsVisible(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, defaultDuration);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        return elements;
    }
    public void waitForElementAbsent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, defaultDuration);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public WebElement waitForElementPresent(long mills, WebElement element) {
        int milsWaitStep = 500;
        long numberOfLoops = mills / milsWaitStep;
        for (int i = 0; i < numberOfLoops; i++) {
            try {
                element.getLocation();
                return element;
            } catch (Exception ex) {
                waitFor(milsWaitStep);
            }
        }
        throw new AssertionError("Target element absent");
    }
    public WebElement waitForElementPresent(WebElement element) {
        waitForElementPresent(30000, element);
        waitFor(1000);
        return element;
    }



}

