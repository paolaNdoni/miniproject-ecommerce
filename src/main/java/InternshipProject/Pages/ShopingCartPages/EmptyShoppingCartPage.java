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

    public EmptyShoppingCartPage() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    public void removeProductsFromCart() {
        WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10));
        int expectedRemainingProducts;

        try {
            List<WebElement> productRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]")));

            Assert.assertFalse("No products found in cart to remove", productRows.isEmpty());
            int initialCount = productRows.size();

            for (int i = 0; i < initialCount; i++) {
                try {
                    expectedRemainingProducts = initialCount - (i + 1);
                    BaseInformation.getDriver().navigate().refresh();
                    basePageObject.getWaitUtils().waitForPageToLoad();

                    WebElement firstProductRow = basePageObject.getWaitUtils().waitForElementVisible(ShoppingCartElements.firstProduct);
                    basePageObject.getWebElementUtils().scrollTo(firstProductRow);
                    WebElement removeButton = basePageObject.getWaitUtils().find(
                            By.xpath("(//tr[contains(@class, 'odd') or contains(@class, 'even')])[1]" +
                                    "//td[contains(@class, 'product-cart-remove')]//a[@title='Remove Item']"));

                    basePageObject.getWebElementUtils().scrollTo(removeButton);
                    removeButton.click();

                    try {
                        WebElement confirmButton = basePageObject.getWaitUtils().find(
                                ShoppingCartElements.confirmButton);
                        confirmButton.click();
                    } catch (Exception ignore) {
                    }

                    basePageObject.getWaitUtils().waitForPageToLoad();
                    Thread.sleep(1000); //force refresh
                    BaseInformation.getDriver().navigate().refresh();
                    basePageObject.getWaitUtils().waitForPageToLoad();

                    if (expectedRemainingProducts > 0) {
                        List<WebElement> remainingProducts = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                                By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]")));

                        if (remainingProducts.size() != expectedRemainingProducts) {
                            BaseInformation.getDriver().navigate().refresh();
                            basePageObject.getWaitUtils().waitForPageToLoad();

                            remainingProducts = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                                    By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]")));
                        }

                        Assert.assertEquals("Incorrect number of products remaining after removal",
                                expectedRemainingProducts, remainingProducts.size());
                    }

                } catch (Exception e) {
                    Assert.fail("Failed to remove product " + (i + 1) + ": " + e.getMessage());
                }
            }

            BaseInformation.getDriver().navigate().refresh();
            basePageObject.getWaitUtils().waitForPageToLoad();
            List<WebElement> finalProductCheck = BaseInformation.getDriver().findElements(
                    By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]"));
            Assert.assertEquals("Not all products were removed from the cart", 0, finalProductCheck.size());

        } catch (Exception e) {
            Assert.fail("Exception during product removal from cart: " + e.getMessage());
        }
    }
}
