package Other.PROJECT.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.time.Duration;

public class BasePageObject {
    private ElementLocatorFactory rootFactory;
    private final Duration defaultDuration = Duration.ofMillis(2L);
    private BaseInformation baseInformation;
    private WaitUtils waitUtils;
    private WebElementUtils webElementUtils;
    private final WebDriver driver = BaseInformation.getDriver();

    public BasePageObject(BaseInformation baseInformation) {
        this.baseInformation = baseInformation;
        this.rootFactory = new DefaultElementLocatorFactory(this.driver);
        PageFactory.initElements(this.rootFactory, this);
    }

    public BasePageObject(BaseInformation baseInformation, By locator) {
        this.baseInformation = baseInformation;

        try {
            this.getWaitUtils().waitForElementVisible(locator);
        } catch (Exception var4) {
        }

        this.rootFactory = new DefaultElementLocatorFactory(this.driver.findElement(locator));
        PageFactory.initElements(this.rootFactory, this);
    }

    public BasePageObject(BaseInformation baseInformation, WebElement rootElement) {
        this.baseInformation = baseInformation;
        this.rootFactory = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(this.rootFactory, this);
    }

    public void getUrl(String url) {
        this.driver.get(url);
    }

    public WaitUtils getWaitUtils() {
        if (this.waitUtils == null) {
            this.waitUtils = new WaitUtils(this.getBaseInformation(), this.defaultDuration);
        }

        return this.waitUtils;
    }

    public WebElementUtils getWebElementUtils() {
        if (this.webElementUtils == null) {
            this.webElementUtils = new WebElementUtils(this.getBaseInformation(), this.defaultDuration);
        }

        return this.webElementUtils;
    }

    public BaseInformation getBaseInformation() {
        return this.baseInformation;
    }
}
