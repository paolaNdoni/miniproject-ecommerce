package InternshipProject.Elements;

import InternshipProject.Utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartElements {
    public ShoppingCartElements() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    @FindBy(xpath = "//a[contains(@href, 'cart')]")
    public  WebElement shoppingCart;
    @FindBy(xpath = "//input[@id='qinput-242023']")
    public WebElement quantity;
    @FindBy(xpath = "//button[@id='qbutton-242023']")
    public WebElement okButton;
}
