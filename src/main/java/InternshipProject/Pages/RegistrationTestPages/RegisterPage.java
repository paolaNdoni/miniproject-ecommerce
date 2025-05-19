package InternshipProject.Pages.RegistrationTestPages;

import InternshipProject.Elements.RegisterPageElements;
import InternshipProject.Globals.Globals;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;


public class RegisterPage {
    BasePageObject basePageObject = new BasePageObject();
    RegisterPageElements registerPageElements =  new RegisterPageElements();

    public RegisterPage(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    public void getUrl(String url){
        BaseInformation.getDriver().get(url);
    }
    public void clickAccountButton(){
        basePageObject
                .getWaitUtils()
                .waitForElementClickable(registerPageElements.account)
                .click();
    }
    public void clickRegisterButton(){
        basePageObject
                .getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, registerPageElements.register)
                .click();
    }
    public void checkPageTitle(String expectedTitle){
        String title = BaseInformation.getDriver().getTitle();
        Assert.assertEquals("Page title is not correct!", expectedTitle, title);
    }

    public void setFirstName(String firstName){
        basePageObject
                .getWaitUtils()
                .waitForElementVisible(registerPageElements.firstName)
                .sendKeys(firstName);
    }
    public void setMiddleName(String middleName){
        basePageObject
                .getWaitUtils()
                .waitForElementVisible(registerPageElements.middleName)
                .sendKeys(middleName);
    }
    public void setLastName(String lastName){
        basePageObject
                .getWebElementUtils()
                .sendKeysToElementWithWait(registerPageElements.lastName,lastName,2);
    }

    public void setEmail(){
        basePageObject
                .getWaitUtils()
                .waitForElementVisible(registerPageElements.email)
                .sendKeys(Globals.email);
    }

    public void setPassword(){
        registerPageElements
                .password
                .sendKeys(Globals.password);

    }

    public void setConfirmPassword(){
        registerPageElements.confirmPassword.sendKeys(Globals.password);
    }

    public void clickRegisterButtonForm(){
        basePageObject
                .getWebElementUtils()
                .scrollToElement(registerPageElements.registerButton);
        basePageObject
                .getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, registerPageElements.registerButton)
                .click();
    }

    public void checkRegister(){
        Assert.assertTrue( "Success message is not displayed!", registerPageElements.successMessage.isDisplayed());
    }

    public void clickLogOut(){
        basePageObject
                .getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, registerPageElements.logoutLink)
                .click();
    }
}
