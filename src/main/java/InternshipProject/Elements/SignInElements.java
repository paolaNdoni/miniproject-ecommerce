package InternshipProject.Elements;

import InternshipProject.Utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInElements {
    public SignInElements(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    @FindBy(xpath = "//a[@title='Log In']")
    public WebElement login;
    @FindBy(xpath = "//input[@name='login[username]']")
    public WebElement username;
    @FindBy(xpath = "//input[@id='pass']")
    public WebElement password;
    @FindBy(xpath = "//button[@id='send2']")
    public WebElement loginButton;
    @FindBy(xpath = "//div[@class='welcome-msg']")
    public WebElement welcomeMessage;
    @FindBy(xpath = "//a[@title='Log Out']")
    public WebElement logoutLink;

}
