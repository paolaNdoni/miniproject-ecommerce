package MiniProject.Pages;

import MiniProject.Elements.RegisterElements;
import MiniProject.Globals.Globals;
import MiniProject.Utilities.BaseInformation;
import MiniProject.Utilities.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    BasePageObject basePageObject;
    RegisterElements registerElements;

    public RegisterPage() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    public void getUrl(String url){
        BaseInformation.getDriver().get(url);
    }

    public void navigateToAccount() {
        if (registerElements.account == null) {
            throw new RuntimeException("registerElements.account is null. PageFactory may have failed.");
        }
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(15, registerElements.account)
                .click();
    }
    public void navigateToRegister() {
        basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(5000, registerElements.register).click();
    }



    public void setFirstName(String firstName){
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000,registerElements.firstName)
                .sendKeys(firstName);
    }
    public void setMiddleName(String middleName){
        basePageObject.getWaitUtils()
                .waitForElementVisible(registerElements.middleName)
                .sendKeys(middleName);
    }
    public void setLastName(String lastName){
        basePageObject.getWaitUtils()
                .waitForElementVisible(registerElements.lastName)
                .sendKeys(lastName);
    }
    public void setEmail(){
        basePageObject.getWaitUtils()
                .waitForElementVisible(registerElements.email)
                .sendKeys(Globals.email);
    }
    public void setPassword(){
        basePageObject.getWaitUtils()
                .waitForElementVisible(registerElements.password)
                .sendKeys(Globals.password);
    }
    public void setConfirmPassword(){
        String confirmedPassword = registerElements.password.getAttribute("value");
        assert confirmedPassword != null;
        basePageObject.getWaitUtils()
                .waitForElementVisible(registerElements.confirmPassword)
                .sendKeys(confirmedPassword);
    }
    public void clickRegister(){
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, registerElements.registerButton)
                .click();
    }
    public boolean checkSuccessMessage(){
        return basePageObject.getWaitUtils()
                .waitForElementVisible(registerElements.successMessage)
                .isDisplayed();
    }
    public void clickLogOut(){
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, registerElements.logoutLink)
                .click();
    }

}

