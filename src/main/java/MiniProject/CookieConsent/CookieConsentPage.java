package MiniProject.CookieConsent;

import MiniProject.Utilities.BaseInformation;
import MiniProject.Utilities.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CookieConsentPage {
    WebDriver driver;
    BasePageObject basePageObject = new BasePageObject(BaseInformation.getBaseInformation());

    @FindBy(id = "privacy_pref_optin")
    WebElement optInRadio;

    @FindBy(id = "consent_prompt_submit")
    WebElement submitButton;

    public CookieConsentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        basePageObject.getWaitUtils().waitForElementClickable(optInRadio).click();
        basePageObject.getWaitUtils().waitForElementClickable(submitButton).click();
    }
}
