package InternshipProject.Elements;

import InternshipProject.Utilities.BaseInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckSaleElements {
    public CheckSaleElements(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    public static final By productLocator = By.cssSelector("ul.products-grid > li.item");
    @FindBy(xpath = "//p[@class='old-price']")
    public WebElement regularPrice;
    @FindBy(xpath = "//p[@class='special-price']")
    public WebElement salePrice;
}
