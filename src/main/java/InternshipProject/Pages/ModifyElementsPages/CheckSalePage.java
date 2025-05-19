package InternshipProject.Pages.ModifyElementsPages;

import InternshipProject.Elements.CheckSaleElements;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static InternshipProject.Elements.CheckSaleElements.productLocator;

public class CheckSalePage {
    BasePageObject basePageObject = new BasePageObject();
    CheckSaleElements checkSaleElements = new CheckSaleElements();

    public CheckSalePage() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }


    public void checkDiscount() {

        WebDriver driver = BaseInformation.getDriver();
        basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(7000, productLocator);
        List<WebElement> productsList = driver.findElements(productLocator);
        for (int i = 0; i < productsList.size(); i++) {
            try {
                List<WebElement> currentProducts = driver.findElements(productLocator);
                if (i >= currentProducts.size()) {
                    break;
                }
                WebElement product = currentProducts.get(i);
                basePageObject.getWebElementUtils().scrollToElement(product);
                WebElement priceBox = product.findElement(By.cssSelector("div.price-box"));
                WebElement regularPrice = priceBox.findElement(By.cssSelector("p.old-price span.price"));
                WebElement specialPrice = priceBox.findElement(By.cssSelector("p.special-price span.price"));
                Assert.assertTrue("Regular price not displayed", regularPrice.isDisplayed());
                Assert.assertTrue("Special price not displayed", specialPrice.isDisplayed());
            } catch (StaleElementReferenceException e) {
                if (i > 0) i--;
                basePageObject.getWaitUtils().waitForSeconds(1);
            } catch (IndexOutOfBoundsException | NoSuchElementException e) {
                Assert.fail("Issue with product " + i + ": " + e.getMessage());
            }
        }
    }



    public void verifyRegularColor() {
        String color = basePageObject
                .getWebElementUtils().getCssValue(checkSaleElements.regularPrice, "color");
        String textDecoration = basePageObject
                .getWebElementUtils().getCssValue(checkSaleElements.regularPrice, "text-decoration-style");

        if (!(color.contains("rgba(160, 160, 160, 1)") && textDecoration.contains("solid"))) {
            Assert.fail("Original price does not have gray color and strikethrough. " +
                    "Actual color: " + color + ", text-decoration-style: " + textDecoration);
        }
    }
    public void verifySaleColor() {
        String color = basePageObject
                .getWebElementUtils().getCssValue(checkSaleElements.salePrice, "color");
        String textDecoration = basePageObject
                .getWebElementUtils().getCssValue(checkSaleElements.salePrice, "text-decoration-style");
        if ((color.contains("rgba(60, 160, 160, 1)") && textDecoration.contains("solid"))) {
            Assert.fail("Sale price has grey color and line-through. " +
                    "Actual color: " + color + ", text-decoration-style: " + textDecoration);
        }
    }


}

