package InternshipProject.Pages.ShopingCartPages;

import InternshipProject.Elements.ShoppingCartElements;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EmptyShoppingCartPage {
    BasePageObject basePageObject = new BasePageObject();
    ShoppingCartElements shoppingCartElements = new ShoppingCartElements();
    private WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10));

    public EmptyShoppingCartPage() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    private WebElement find(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void removeProductsFromCart() {
        WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10));
        int expectedRemainingProducts;

        try {
            List<WebElement> productRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]")));

            Assert.assertFalse("No products found in cart to remove", productRows.isEmpty());
            for (int i = 0; i < productRows.size(); i++) {
                expectedRemainingProducts = productRows.size() - (i + 1);
                try {
                    WebElement firstProductRow = basePageObject.getWaitUtils().waitForElementVisible(ShoppingCartElements.firstProduct);
                    basePageObject.getWebElementUtils().scrollTo(firstProductRow);
                    WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("(//tr[contains(@class, 'odd') or contains(@class, 'even')])[1]" +
                                    "//td[contains(@class, 'product-cart-remove')]//a[@title='Remove Item']")));

                    basePageObject.getWebElementUtils().scrollTo(removeButton);
                    removeButton.click();

                    if (expectedRemainingProducts > 0) {
                        //refresh
                        List<WebElement> remainingProducts = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                                By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]")));

                        Assert.assertEquals("Incorrect number of products remaining after removal",
                                expectedRemainingProducts, remainingProducts.size());
                    } else {

                        wait.until(ExpectedConditions.or(
                                ExpectedConditions.numberOfElementsToBe(By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]"), 0)
                        ));
                    }

                } catch (Exception e) {
                    Assert.fail("Failed to remove product " + (i + 1) + ": " + e.getMessage());
                }
            }
            try {
                List<WebElement> finalProductCheck = BaseInformation.getDriver().findElements(
                        By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]"));
                Assert.assertEquals("Not all products were removed from the cart", 0, finalProductCheck.size());
            } catch (Exception e) {
                // If we can't find the product rows at all, cart is likely empty which is good
            }

        } catch (Exception e) {
            Assert.fail("Exception during product removal from cart: " + e.getMessage());
        }
    }
}
