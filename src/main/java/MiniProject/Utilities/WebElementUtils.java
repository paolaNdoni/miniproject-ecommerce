package MiniProject.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;


public class WebElementUtils {
    WebDriver driver = BaseInformation.getDriver();
    private final BaseInformation baseInformation;
    private final WaitUtils waitUtils;

    public WebElementUtils(BaseInformation baseInformation, Duration defaultDuration){
        super();
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


    public void sendKeysToElementWithWait(WebElement element, String value, long millsWait) {
        element.sendKeys(value);
        WaitUtils.waitFor((int) millsWait);
    }


    public boolean isElementVisibleWithWait(int mills, WebElement element) {
        try {
            waitUtils.waitForElementVisibleWithCustomTime(mills, element);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


//    public static void click(WebDriver driver, WebElement element) {
//        WaitUtils.waitForClickable(driver, element, 10);
//        element.click();
//    }
//    public void click(WebElement element) {
//        waitUtils.waitForElementClickable(element)
//                .click();
//    }
//
//    public static void sendKeys(WebDriver driver, WebElement element, String value) {
//        WaitUtils.waitForVisible(driver, element, 10);
//        element.clear();
//        element.sendKeys(value);
//    }
//
//    public static boolean isDisplayed(WebDriver driver, WebElement element) {
//        try {
//            WaitUtils.waitForVisibility(driver, element, 10);
//            return element.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }
}
