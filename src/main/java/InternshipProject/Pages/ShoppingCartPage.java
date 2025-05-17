package InternshipProject.Pages;

import InternshipProject.Elements.WishlistElements;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCartPage {
    BasePageObject basePageObject = new BasePageObject();
    WishlistElements wishlistElements = new WishlistElements();

    public ShoppingCartPage() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    public void clickWishList() {
        basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(5000, wishlistElements.wishlist)
                .click();
    }
    public void editProducts(){
        List<WebElement> productTable = BaseInformation.getDriver()
                .findElements(By.cssSelector(("td[class*='odd'], td[class*='even']")));
        for(int i = 0; i < productTable.size(); i++) {
            productTable = BaseInformation.getDriver()
                    .findElements(By.cssSelector("td[class*='odd'], td[class*='even']"));
            WebElement product = productTable.get(i);
            basePageObject.getWebElementUtils().scrollToElement(product);
            basePageObject.getWaitUtils()
                    .waitForElementVisibleWithCustomTime(5000, wishlistElements.editButton)
                    .click();
            WebElement black = BaseInformation.getDriver().findElement(By.xpath("//img[@alt='Black']"));
            basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(5000, black).click();
            WebElement sizeS = BaseInformation.getDriver().findElement(By.xpath("//span[contains(text(),'S')]"));
            basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(5000, sizeS).click();
            WebElement white = BaseInformation.getDriver().findElement(By.xpath("//img[@alt='White']"));
            BaseInformation.getDriver().navigate().back();
            if (i == 1) {
                basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(5000, white).click();
            }
            basePageObject.getWaitUtils().waitForSeconds(1);
            basePageObject.getWebElementUtils().scrollToElement(wishlistElements.addToCartButton);
            basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(5000, wishlistElements.addToCartButton)
                    .click();
        }



        }
    }
