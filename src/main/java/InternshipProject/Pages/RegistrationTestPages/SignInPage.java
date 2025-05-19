package InternshipProject.Pages.RegistrationTestPages;

import InternshipProject.Elements.SignInElements;
import InternshipProject.Globals.Globals;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    BasePageObject basePageObject = new BasePageObject();
    SignInElements signInElements = new SignInElements();
    public SignInPage() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    public void getUrl(String url){
        BaseInformation.getDriver().get(url);
    }
    public void deleteCookies() {
        WebDriver driver = BaseInformation.getDriver();
        driver.manage().deleteAllCookies();
    }
    public void clickLogin(){
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(10000, signInElements.login)
                .click();
    }
    public void setEmail(String email){
        basePageObject.getWaitUtils()
                .waitForElementVisible(signInElements.username)
                .sendKeys(email);
    }
    public void setPassword(String password){
        basePageObject.getWaitUtils()
                .waitForElementVisible(signInElements.password)
                .sendKeys(password);
    }
    public void clickLoginButton(){
        basePageObject.getWebElementUtils()
                        .scrollToElement(signInElements.loginButton);
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, signInElements.loginButton)
                .click();
    }
    public void login() {
        clickLogin();
        setEmail(Globals.email);
        setPassword(Globals.password);
        clickLoginButton();
    }
    public void checkMessage(){
        Assert.assertTrue("Your username is not displayed!", basePageObject
                .getWaitUtils().waitForElementVisibleWithCustomTime(5000, signInElements.welcomeMessage)
                .isDisplayed());
    }
    public void clickLogOut(){
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, signInElements.logoutLink)
                .click();
    }
}
