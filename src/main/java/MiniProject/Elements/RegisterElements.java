package MiniProject.Elements;

import MiniProject.Utilities.BaseInformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterElements {
    public RegisterElements(WebDriver driver){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    @FindBy(xpath = "//a[@class='skip-link skip-account']")
    public WebElement account;

    @FindBy(xpath = "//a[@title='Register']")
    public WebElement register;
    @FindBy(id = "firstname")
    public WebElement firstName;

    @FindBy(id = "middlename")
    public WebElement middleName;

    @FindBy(id = "lastname")
    public WebElement lastName;

    @FindBy(id = "email_address")
    public WebElement email;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(name = "confirmation")
    public WebElement confirmPassword;

    @FindBy(xpath = "//button[@title='Register']")
    public WebElement registerButton;

    @FindBy(xpath = "//li[@class='success-msg']")
    public WebElement successMessage;

    @FindBy(xpath = "//a[@title='Log Out']")
    public WebElement logoutLink;
}
