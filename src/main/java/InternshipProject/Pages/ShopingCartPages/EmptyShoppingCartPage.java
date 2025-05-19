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
            int initialCount = productRows.size();

            for (int i = 0; i < initialCount; i++) {
                try {
                    expectedRemainingProducts = initialCount - (i + 1);
                    BaseInformation.getDriver().navigate().refresh();
                    basePageObject.getWaitUtils().waitForPageToLoad();

                    WebElement firstProductRow = basePageObject.getWaitUtils().waitForElementVisible(ShoppingCartElements.firstProduct);
                    basePageObject.getWebElementUtils().scrollTo(firstProductRow);
                    WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("(//tr[contains(@class, 'odd') or contains(@class, 'even')])[1]" +
                                    "//td[contains(@class, 'product-cart-remove')]//a[@title='Remove Item']")));

                    basePageObject.getWebElementUtils().scrollTo(removeButton);
                    removeButton.click();

                    try {
                        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[contains(@class, 'confirm') or contains(@class, 'ok') or contains(text(), 'OK')]")));
                        confirmButton.click();
                    } catch (Exception ignore) {
                        // No confirmation dialog, which is fine
                    }

                    // Wait for page to update
                    basePageObject.getWaitUtils().waitForPageToLoad();

                    // Wait for any loading indicators to disappear
                    try {
                        WebElement loader = BaseInformation.getDriver().findElement(By.cssSelector(".loading-mask"));
                        wait.until(ExpectedConditions.invisibilityOf(loader));
                    } catch (Exception ignore) {
                        // No loader found, which is fine
                    }

                    // Important: Give the page a moment to stabilize
                    Thread.sleep(1000);

                    // Force refresh the page to ensure we have the latest state
                    BaseInformation.getDriver().navigate().refresh();
                    basePageObject.getWaitUtils().waitForPageToLoad();

                    if (expectedRemainingProducts > 0) {
                        // Check remaining products after refresh
                        List<WebElement> remainingProducts = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                                By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]")));

                        // If count doesn't match, try one more refresh
                        if (remainingProducts.size() != expectedRemainingProducts) {
                            BaseInformation.getDriver().navigate().refresh();
                            basePageObject.getWaitUtils().waitForPageToLoad();

                            remainingProducts = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                                    By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]")));
                        }

                        // Now verify the count
                        Assert.assertEquals("Incorrect number of products remaining after removal",
                                expectedRemainingProducts, remainingProducts.size());
                    } else {
                        // Verify cart is empty
                        wait.until(ExpectedConditions.or(
                                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'empty') and contains(text(), 'cart')]")),
                                ExpectedConditions.numberOfElementsToBe(By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]"), 0)
                        ));
                    }

                } catch (AssertionError e) {
                    throw e; // Re-throw assertion errors
                } catch (Exception e) {
                    Assert.fail("Failed to remove product " + (i + 1) + ": " + e.getMessage());
                }
            }

            // Final verification
            BaseInformation.getDriver().navigate().refresh();
            basePageObject.getWaitUtils().waitForPageToLoad();

            List<WebElement> finalProductCheck = BaseInformation.getDriver().findElements(
                    By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]"));
            Assert.assertEquals("Not all products were removed from the cart", 0, finalProductCheck.size());

        } catch (AssertionError e) {
            throw e; // Re-throw assertion errors
        } catch (Exception e) {
            Assert.fail("Exception during product removal from cart: " + e.getMessage());
        }
    }
}
