package InternshipProject.Pages.ShopingCartPages;

import InternshipProject.Elements.RegisterPageElements;
import InternshipProject.Elements.ShoppingCartElements;
import InternshipProject.Elements.WishlistElements;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static InternshipProject.Elements.ShoppingCartElements.*;

public class ShoppingCartPage {
    BasePageObject basePageObject = new BasePageObject();
    WishlistElements wishlistElements = new WishlistElements();
    RegisterPageElements registerPageElements = new RegisterPageElements();
    private WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10));

    private WebElement find(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public ShoppingCartPage() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    public void clickAccount() {
        basePageObject.getWebElementUtils().scrollToElement(registerPageElements.account);
        basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(5000, registerPageElements.account);
        registerPageElements.account.click();
    }

    public void clickWishList() {
        basePageObject.getWebElementUtils().scrollToElement(wishlistElements.wishlist);
        basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(5000, wishlistElements.wishlist);
        wishlistElements.wishlist.click();
    }

    public void editProducts() {
        basePageObject.getWaitUtils().waitForPageToLoad();
        int editsRemaining = 2;
        int processed = 0;
        while (processed < editsRemaining) {
            try {
                List<WebElement> productRows = BaseInformation.getDriver().findElements(ShoppingCartElements.productRow);
//                if (productRows.isEmpty()) {
//                    Assert.fail("No products found in wishlist, retrying...");
//                    continue;
//                }
                WebElement currentRow = productRows.get(0);
                basePageObject.getWebElementUtils().scrollToElement(currentRow);

                WebElement editButton = basePageObject.getWebElementUtils().findClickableElement(
                        BaseInformation.getDriver(), ShoppingCartElements.editButton);
                if (editButton == null) {
                    Assert.fail("Edit button not found!");
                    continue;
                }
                basePageObject.getWebElementUtils().safeClick(editButton);
                basePageObject.getWaitUtils().waitForPageToLoad();

                String color = (processed == 0) ? "Black" : "White";
                WebElement colorOption = basePageObject.getWebElementUtils()
                        .findClickableElement(BaseInformation.getDriver(), By.cssSelector("img[alt='" + color + "']"));
                if (colorOption == null) {
                    Assert.fail("Color option not found!");
                    continue;
                }
                basePageObject.getWebElementUtils().scrollToElement(colorOption);
                basePageObject.getWebElementUtils().safeClick(colorOption);
                WebElement sizeOption = basePageObject.getWebElementUtils()
                        .findClickableElement(BaseInformation.getDriver(), ShoppingCartElements.size);
                if (sizeOption == null) {
                    Assert.fail("Size option not found, retrying...");
                    continue;
                }
                basePageObject.getWebElementUtils().scrollToElement(sizeOption);
                basePageObject.getWebElementUtils().safeClick(sizeOption);
                WebElement addToCart = basePageObject.getWebElementUtils().findClickableElement(BaseInformation.getDriver(), ShoppingCartElements.submitButton);
                if (addToCart == null) {
                    Assert.fail("Add to cart button not found, retrying...");
                    continue;
                }
                basePageObject.getWebElementUtils().scrollToElement(addToCart);
                basePageObject.getWebElementUtils().safeClick(addToCart);
                basePageObject.getWaitUtils().waitForPageToLoad();

                processed++;
                Assert.assertTrue("Successfully processed product " + processed + " of " + editsRemaining, true);
                navigateToWishlist(BaseInformation.getDriver());

            } catch (Exception e) {
                Assert.fail("Error: " + e.getMessage());
                try {
                    navigateToWishlist(BaseInformation.getDriver());
                } catch (Exception recoveryEx) {
                    Assert.fail("Recovery failed: " + recoveryEx.getMessage());
                }
            }
        }
    }


    private void navigateToWishlist(WebDriver driver) {
        for (int i = 0; i < 3; i++) {
            try {
                clickAccount();
                clickWishList();
                basePageObject.getWaitUtils().waitForPageToLoad();
                if (!driver.findElements(ShoppingCartElements.productRow).isEmpty()) {
                    return;
                }
            } catch (Exception e) {
                //
            }
        }
        Assert.fail("Failed to navigate to wishlist");
    }

    public void clickShoppingCart() {
        WebElement cart = find(ShoppingCartElements.cart);
        basePageObject.getWebElementUtils().scrollTo(cart);
        cart.click();
    }

    public void changeQuantity(String qtyText) {
        WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10));
        Actions actions = new Actions(BaseInformation.getDriver());
         try {
            WebElement qty = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@class='input-text qty'])[2]")));
            basePageObject.getWebElementUtils().scrollTo(qty);
            qty.click();

            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("mac")) {
                actions.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).perform();
            } else {
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            }
            actions.sendKeys(Keys.DELETE).perform();

            for (char c : qtyText.toCharArray()) {
                actions.sendKeys(String.valueOf(c)).perform();
            }
            WebElement updateButton = basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(5000, ShoppingCartElements.updateButton);
            basePageObject.getWebElementUtils().scrollTo(updateButton);
            updateButton.click();


         } catch (Exception e) {
             System.out.println("Failed to change quantity"  + e.getMessage());
         }
    }
    public void verifyCartTotals() {
        WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10));
        double sumOfProductPrices = 0.0;

        try {
            WebElement productTable = basePageObject.getWaitUtils().waitForElementVisible(shoppingTable);
            basePageObject.getWebElementUtils().scrollTo(productTable);
            List<WebElement> productRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]")));
            Assert.assertFalse("No product rows found in cart", productRows.isEmpty());

            for (int i = 0; i < productRows.size(); i++) {
                try {
                    productRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]")));
                    WebElement row = productRows.get(i);
                    basePageObject.getWebElementUtils().scrollTo(row);

                    WebElement priceElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("(//tr[contains(@class, 'odd') or contains(@class, 'even')])[" + (i + 1) +
                                    "]//td[@class='product-cart-total']//span[@class='price']")));

                    basePageObject.getWebElementUtils().scrollTo(priceElement);
                    String priceText = priceElement.getText().trim();
                    double price = parsePrice(priceText);
                    sumOfProductPrices += price;
                } catch (Exception e) {
                    Assert.fail("Failed to extract price from product row " + (i + 1) + ": " + e.getMessage());
                }
            }

            WebElement cartTotalsSection = basePageObject.getWaitUtils().waitForElementVisible(cartTotal);
            basePageObject.getWebElementUtils().scrollTo(cartTotalsSection);
            WebElement grandTotalElement = basePageObject.getWaitUtils().waitForElementVisible(grandTotal);
            basePageObject.getWebElementUtils().scrollTo(grandTotalElement);
            String grandTotalText = grandTotalElement.getText().trim();
            double grandTotal = parsePrice(grandTotalText);
            double delta = 0.01;
            Assert.assertEquals("Sum of product prices does not match the grand total",
                    grandTotal, sumOfProductPrices, delta);
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            Assert.fail("Exception during cart total verification: " + e.getMessage());
        }
    }

    private double parsePrice(String priceText) {
        String numericPrice = priceText.replaceAll("[^0-9.]", "");
        return Double.parseDouble(numericPrice);
    }
}





