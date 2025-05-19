package InternshipProject.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebElementUtils {
    WebDriver driver = BaseInformation.getDriver();
   private final BasePageObject basePageObject = new BasePageObject();
    private final BaseInformation baseInformation;
    private final WaitUtils waitUtils;


    public WebElementUtils(BaseInformation baseInformation, Duration defaultDuration) {
        this.baseInformation = baseInformation;
        this.waitUtils = new WaitUtils(baseInformation, defaultDuration);
    }

    public void clickWebElement(WebElement webElement) {
        waitUtils.waitForElementClickable(webElement)
                .click();
    }

    public void javaScriptClick(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", webElement);
    }
    public String getCssValue(WebElement element, String cssProperty) {
        return element.getCssValue(cssProperty);
    }

    public void moveMouseToElement(WebElement element) {
        new Actions(driver)
                .moveToElement(element)
                .perform();
        WaitUtils.waitFor(5000);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public void scrollToElementVisible(WebElement element) {
        try {
            if (element == null) {
                System.out.println("Warning: Cannot scroll to null element");
                return;
            }

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            waitUtils.wait(500);
        } catch (Exception e) {
            System.out.println("Error in scrollToElement: " + e.getMessage());
        }
    }
    public void scrollOffset(int x, int y) {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollBy(arguments[0],arguments[1])", x, y);
    }

    public void scrollToBottomOfPage() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public String getTextWithoutSubElements(WebElement element) {
        String fullText = element.getText()
                .trim();
        for (WebElement subElement : element.findElements(By.xpath(".//*"))) {
            fullText = fullText.replace(subElement.getText().trim(), "");
        }
        fullText = fullText.replaceAll("\n", "");
        return fullText;
    }

    public boolean scrollDown(int pixels) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, " + pixels + ");");
            waitUtils.wait(300);
            return true;
        } catch (Exception e) {
            System.out.println("Error in scrollDown: " + e.getMessage());
            return false;
        }
    }
    public void sendKeysToElementWithWait(WebElement element, String value, long millsWait) {
        element.sendKeys(value);
        WaitUtils.waitFor(millsWait);
    }
    public void scrollToElementAndClick(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Scroll the element into view
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            // Optional: Wait for the scrolling action to complete
            Thread.sleep(500);
            // Click the element
            js.executeScript("arguments[0].click();", element);
        } catch (InterruptedException e) {
            // Handle the exception as needed
            Thread.currentThread().interrupt();
        }
    }

    public boolean isElementVisibleWithWait(int mills, WebElement element) {
        try {
            waitUtils.waitForElementVisibleWithCustomTime(mills, element);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }
    public void scrollUp(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -" + pixels + ");");
    }
    public void clickElement(JavascriptExecutor js, WebElement element) throws InterruptedException {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", element);
    }
    public WebElement findClickableElement(WebDriver driver, By locator) {
        for (int i = 0; i < 3; i++) {
            try {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    WebElement element = elements.get(0);
                    basePageObject.getWaitUtils().waitForElementClickable(element);
                    return element;
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                // Continue to next retry
            }
        }
        return null;
    }
    public void scrollTo(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void safeClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    public void setInputValue(WebElement input, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].value = arguments[1]; " +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                input, value
        );
    }
    public boolean safeClickButton(By by) {
        WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10));
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
                try {
                    JavascriptExecutor js = (JavascriptExecutor) BaseInformation.getDriver();
                    js.executeScript("arguments[0].click();", element);
                    return true;
                } catch (Exception jsException) {
                    try {
                        Actions actions = new Actions(BaseInformation.getDriver());
                        actions.moveToElement(element).click().build().perform();
                        return true;
                    } catch (Exception actionsException) {
                        return false;
                    }
                }
        } catch (Exception e) {
            // Element not found or not interactable
            return false;
        }
    }

}
