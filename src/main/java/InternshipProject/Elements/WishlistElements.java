package InternshipProject.Elements;

import InternshipProject.Utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishlistElements {
    public WishlistElements() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    @FindBy(xpath = "//ul[@class='add-to-links']")
    public WebElement wishListContainer;


    @FindBy(css = "a[href*='wishlist']")
    public WebElement wishlist;

    @FindBy(xpath = "//a[@class='link-wishlist']")
    public WebElement wishlistLinks;

    @FindBy(xpath = "//button[@title='Add to Cart']")
    public WebElement addToCartButton;
    @FindBy(xpath = "//a[contains(text(), 'Edit')]")
    public WebElement editButton;
    @FindBy(xpath = "//h1[contains(text(), 'NoLIta Cami')]")
    public WebElement firstProd;
    @FindBy(xpath = "//h1[contains(text(),'Ludlow Oxford Top')]")
    public WebElement secondProd;
}
