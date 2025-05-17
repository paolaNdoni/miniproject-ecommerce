package InternshipProject.ConsentCookies;

import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import InternshipProject.Utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ConsentCookiesPage {
    private BaseInformation baseInformation;
    private WebDriver driver;
    private WaitUtils wait;

    public ConsentCookiesPage() {
        BasePageObject basePageObject = new BasePageObject(); // ✅ No need to pass anything
        this.driver = baseInformation.getDriver();
        this.wait = new WaitUtils(baseInformation, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "privacy_pref_optin")
    private WebElement acceptCookiesButton;
    @FindBy(id = "consent_prompt_submit")
    private WebElement submitButton;
    public void acceptCookiesButton() {
        wait.waitForElementClickable(acceptCookiesButton).click();
    }
    public void submitButton() {
        wait.waitForElementClickable(submitButton).click();
    }



}
