package Other.PROJECT.elements;
//testView Page asserts
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPageElements {
     @FindBy(xpath = "//a[@class='skip-link skip-account']")
     public WebElement accountButton;
     @FindBy(xpath = "//a[@title='Register']")
     public WebElement registerButton;
     @FindBy(css = "input[id='firstname']")
     public WebElement firstNameField;
     @FindBy(css="input[id='middlename']")
     public WebElement middleNameField;
     @FindBy(css = "input[id='lastname']")
     public WebElement lastNameField;
     @FindBy(css="input[id='email_address']")
     public WebElement emailAddressField;
     @FindBy(css="input[name='password']")
     public WebElement passwordField;
     @FindBy(css="input[name='confirmation']")
     public WebElement confirmationPasswordField;
     @FindBy(xpath = "//button[@title='Register']")
     public WebElement confirmRegistrationButton;
     @FindBy(xpath = "//li[@class='success-msg']")
     public WebElement successMessage;
     @FindBy(xpath = "//a[@title='Log out']")
     public WebElement logoutButton;
}

