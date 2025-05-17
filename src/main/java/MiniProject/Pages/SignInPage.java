package MiniProject.Pages;

import MiniProject.Elements.RegisterElements;
import MiniProject.Elements.SignInElements;
import MiniProject.Globals.Globals;
import MiniProject.Utilities.BaseInformation;
import MiniProject.Utilities.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class SignInPage {
    private final WebDriver driver = BaseInformation.getDriver();
    private final BasePageObject basePageObject = new BasePageObject(BaseInformation.getBaseInformation());
    private final RegisterElements registerPage = new RegisterElements(driver);
    private final SignInElements signInElements = new SignInElements(driver);

    public SignInPage() {
        PageFactory.initElements(driver, this); // For @FindBy inside this class (if any)
    }

    public void getUrl(String url) {
        driver.get(url.trim());
    }

    public void clickAccount() {
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5, registerPage.account)
                .click();
    }

    public void clickLogIn() {
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5, signInElements.login)
                .click();
    }

    public void setEmail() {
        basePageObject.getWaitUtils()
                .waitForElementVisible(signInElements.username)
                .sendKeys(Globals.email);
    }

    public void setPassword() {
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5, signInElements.password)
                .sendKeys(Globals.password);
    }
}