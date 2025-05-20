package InternshipProject.Elements;

import InternshipProject.Utilities.BaseInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartElements {
    public ShoppingCartElements() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    public static final By productRow = By.cssSelector("tbody tr");
    public static final By editButton = By.cssSelector("a.link-edit");
    public static final By size = By.xpath("//span[contains(text(),' S ')]");
    public static final By submitButton = By.xpath("//button[contains(@onclick,'submit')]");
    public static final By updateButton = By.xpath("(//button[@name='update_cart_action'][3])");
    public static final By cart = By.xpath("//a[contains(@title, 'My Cart')]");
    public static final By shoppingTable = By.xpath("//table[@id='shopping-cart-table' or contains(@class, 'cart')]");
    public static final By cartTotal = By.xpath("//div[contains(@class, 'cart-totals')]");
    public static final By grandTotal =   By.xpath("//div[contains(@class, 'cart-totals')]//span[@class='price']");
    public static final By firstProduct = By.xpath("(//tr[contains(@class, 'odd') or contains(@class, 'even')])[1]");
    public static final By confirmRemove = By.xpath("//div[@class='page-title']/h1");
}
