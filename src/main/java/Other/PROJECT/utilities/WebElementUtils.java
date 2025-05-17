package Other.PROJECT.utilities;

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

    public WebElementUtils(BaseInformation baseInformation, Duration defaultDuration) {
        this.baseInformation = baseInformation;
        this.waitUtils = new WaitUtils(baseInformation, defaultDuration);
    }

    public void clickWebElement(WebElement webElement) {
        this.waitUtils.waitForElementClickable(webElement).click();
    }

    public void javaScriptClick(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor)this.driver;
        executor.executeScript("arguments[0].click();", new Object[]{webElement});
    }

    public void moveMouseToElement(WebElement element) {
        (new Actions(this.driver)).moveToElement(element).perform();
        WaitUtils.waitFor(5000L);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor)this.driver).executeScript("arguments[0].scrollIntoView(true);", new Object[]{element});
    }

    public void scrollOffset(int x, int y) {
        ((JavascriptExecutor)this.driver).executeScript("window.scrollBy(arguments[0],arguments[1])", new Object[]{x, y});
    }

    public void scrollToBottomOfPage() {
        ((JavascriptExecutor)this.driver).executeScript("window.scrollTo(0, document.body.scrollHeight);", new Object[0]);
    }

    public String getTextWithoutSubElements(WebElement element) {
        String fullText = element.getText().trim();

        for(WebElement subElement : element.findElements(By.xpath(".//*"))) {
            fullText = fullText.replace(subElement.getText().trim(), "");
        }

        fullText = fullText.replaceAll("\n", "");
        return fullText;
    }

    public void sendKeysToElementWithWait(WebElement element, String value, long millsWait) {
        element.sendKeys(new CharSequence[]{value});
        WaitUtils.waitFor(millsWait);
    }

    public boolean isElementVisibleWithWait(int mills, WebElement element) {
        try {
            this.waitUtils.waitForElementVisibleWithCustomTime((long)mills, element);
            return true;
        } catch (Exception var4) {
            return false;
        }
    }
}
