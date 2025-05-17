package MiniProject.Elements;

import MiniProject.Utilities.BaseInformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInElements {
    public SignInElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[@title='Log In']")
    public WebElement login;
    @FindBy(xpath = "//input[@name='login[username]']")
    public WebElement username;
    @FindBy(xpath = "//input[@id='pass']")
    public WebElement password;
}
