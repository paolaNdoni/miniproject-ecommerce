package InternshipProject.Elements;

import InternshipProject.Utilities.BaseInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartElements {
    public ShoppingCartElements() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    public static final By productRow = By.cssSelector("tbody tr");
    public static final By editButton = By.cssSelector("a.link-edit");
    public static final By size = By.xpath("//span[contains(text(),' S ')]");
    public static final By submitButton = By.xpath("//button[contains(@onclick,'submit')]");
    public static final By okButton = By.cssSelector("[id^='qbutton-'], .confirm-button, .ok-button");
    public static final By inputQuantity = By.cssSelector("input[id^='qinput-']");
}
