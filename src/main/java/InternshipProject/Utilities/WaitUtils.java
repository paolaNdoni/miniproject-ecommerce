package InternshipProject.Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class WaitUtils {
    private BaseInformation baseInformation;
    private Duration defaultDuration;
    private BasePageObject basePageObject = new BasePageObject();

    private WebDriver driver = BaseInformation.getDriver();
    public WaitUtils(BaseInformation baseInformation, Duration defaultDuration) {
        this.baseInformation = baseInformation;
        this.defaultDuration = defaultDuration;
    }

    public static void waitFor(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e1) {
            System.out.println("ERROR in waitForMethod");
        }
    }

    public WebElement waitForElementVisibleWithCustomTime(long mills, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(mills));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementVisibleWithCustomTime(long mills, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(mills));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementClickableWithCustomTime(long mills, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(mills));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementClickable(WebElement element) {
        return waitForElementClickableWithCustomTime(defaultDuration.toMillis(), element);
    }

    public WebElement waitForElementVisible(WebElement element) {
        return waitForElementVisibleWithCustomTime(defaultDuration.toMillis(), element);
    }

    public WebElement waitForElementVisible(By locator) {
        return waitForElementVisibleWithCustomTime(defaultDuration.toMillis(), locator);
    }

    public void waitForAttributePresentWithCustomWaitTime(long mills, WebElement element,String nameOfAttribute) {
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
    public void waitForElementsVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    public List<WebElement> waitForAllElementsVisibleWithCustomTime(int timeoutInMillis, By locator) {
        WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofMillis(timeoutInMillis));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        return BaseInformation.getDriver().findElements(locator);
    }


    public void waitForNumberOfElementsToBe(By locator, int expectedCount, long mills) {
        new WebDriverWait(BaseInformation.getDriver(), Duration.ofMillis(mills))
                .until(ExpectedConditions.numberOfElementsToBe(locator, expectedCount));
    }


    public void waitForElementPresent(long mills, WebElement element) {
        int milsWaitStep = 500;
        long numberOfLoops = mills / milsWaitStep;
        for (int i = 0; i < numberOfLoops; i++) {
            try {
                element.getLocation();
                return;
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
    public WebElement waitForElementVisibleFluently(By locator, int timeoutSeconds, int pollingMillis) {
        FluentWait<WebDriver> wait = new FluentWait<>(BaseInformation.getDriver())
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            if (element.isDisplayed()) {
                return element;
            }
            return null;
        });
    }
    public void waitForPageReadyState() {
        WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10));
        wait.until(driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        });
    }
    public void waitForCondition(Function<WebDriver, Boolean> condition, int timeoutSeconds) {
        new FluentWait<>(BaseInformation.getDriver())
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class)
                .until(condition);
    }
    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Wait interrupted", e);
        }
    }
    public void waitForPageToLoad() {
        new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }
    public WebElement waitAndFindElement(WebDriver driver, By locator) {
        try {
            basePageObject.getWaitUtils().waitForPageToLoad();
            List<WebElement> elements = driver.findElements(locator);
            if (elements.isEmpty()) {
                return null;
            }
            WebElement element = elements.get(0);
            basePageObject.getWebElementUtils().scrollToElement(element);
            basePageObject.getWaitUtils().waitForElementClickable(element);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

}
