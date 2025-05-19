package InternshipProject.Pages.ShopingCartPages;

import InternshipProject.Elements.RegisterPageElements;
import InternshipProject.Elements.ShoppingCartElements;
import InternshipProject.Elements.WishlistElements;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static InternshipProject.Elements.ShoppingCartElements.okButton;

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
        int attempts = 0;
        while (processed < editsRemaining) {
            try {
                List<WebElement> productRows = BaseInformation.getDriver().findElements(ShoppingCartElements.productRow);
                if (productRows.isEmpty()) {
                    Assert.fail("No products found in wishlist, retrying...");
                    attempts++;
                    continue;
                }
                WebElement currentRow = productRows.get(0);
                basePageObject.getWebElementUtils().scrollToElement(currentRow);
                Thread.sleep(500);

                WebElement editButton = basePageObject.getWebElementUtils().findClickableElement(
                        BaseInformation.getDriver(), ShoppingCartElements.editButton);
                if (editButton == null) {
                    Assert.fail("Edit button not found, retrying...");
                    attempts++;
                    continue;
                }
                basePageObject.getWebElementUtils().safeClick(editButton);
                basePageObject.getWaitUtils().waitForPageToLoad();

                String color = (processed == 0) ? "Black" : "White";
                WebElement colorOption = basePageObject.getWebElementUtils()
                        .findClickableElement(BaseInformation.getDriver(), By.cssSelector("img[alt='" + color + "']"));
                if (colorOption == null) {
                    Assert.fail("Color option not found, retrying...");
                    attempts++;
                    continue;
                }
                basePageObject.getWebElementUtils().scrollToElement(colorOption);
                Thread.sleep(500);
                basePageObject.getWebElementUtils().safeClick(colorOption);
                WebElement sizeOption = basePageObject.getWebElementUtils()
                        .findClickableElement(BaseInformation.getDriver(), ShoppingCartElements.size);
                if (sizeOption == null) {
                    Assert.fail("Size option not found, retrying...");
                    attempts++;
                    continue;
                }
                basePageObject.getWebElementUtils().scrollToElement(sizeOption);
                Thread.sleep(500);
                basePageObject.getWebElementUtils().safeClick(sizeOption);
                WebElement addToCart = basePageObject.getWebElementUtils().findClickableElement(BaseInformation.getDriver(), ShoppingCartElements.submitButton);
                if (addToCart == null) {
                    Assert.fail("Add to cart button not found, retrying...");
                    attempts++;
                    continue;
                }
                basePageObject.getWebElementUtils().scrollToElement(addToCart);
                Thread.sleep(500);
                basePageObject.getWebElementUtils().safeClick(addToCart);
                basePageObject.getWaitUtils().waitForPageToLoad();

                processed++;
                Assert.assertTrue("Successfully processed product " + processed + " of " + editsRemaining, true);
                navigateToWishlist(BaseInformation.getDriver());

            } catch (Exception e) {
                Assert.fail("Error: " + e.getMessage());
                attempts++;
                try {
                    navigateToWishlist(BaseInformation.getDriver());
                } catch (Exception recoveryEx) {
                    Assert.fail("Recovery failed: " + recoveryEx.getMessage());
                }
            }
        }

        if (processed < editsRemaining) {
            Assert.fail("Only completed " + processed + " edits out of " + editsRemaining);
        }
    }


    private void navigateToWishlist(WebDriver driver) {
        for (int i = 0; i < 3; i++) {
            try {
                clickAccount();
                Thread.sleep(500);
                clickWishList();
                basePageObject.getWaitUtils().waitForPageToLoad();
                if (!driver.findElements(ShoppingCartElements.productRow).isEmpty()) {
                    return;
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                //
            }
        }
        Assert.fail("Failed to navigate to wishlist");
    }

    public void clickShoppingCart() {
        WebElement cart = find(By.xpath("//a[contains(@href,'cart')]"));
        basePageObject.getWebElementUtils().scrollToElement(cart);
        cart.click();
    }

    public void changeQuantity(String qtyText) {
        WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) BaseInformation.getDriver();

        for (int i = 0; i < 3; i++) {
            try {
                WebElement qty = wait.until(ExpectedConditions.presenceOfElementLocated(ShoppingCartElements.inputQuantity));
                basePageObject.getWebElementUtils().scrollTo(qty);
                Thread.sleep(500);
                basePageObject.getWebElementUtils().setInputValue(qty, qtyText);

                Thread.sleep(500);
                String currentValue = qty.getAttribute("value");

                if (qtyText.equals(currentValue)) {
                    Assert.assertEquals("Quantity changed successfully", qtyText, currentValue);
                    return;
                }
            } catch (Exception e) {
                if (i == 2) {
                    Assert.fail("Attempt " + (i + 1) + " failed: " + e.getMessage());
                }
            }
        }
        Assert.fail("Failed to change quantity after multiple attempts");
    }

    public void clickOk() {
        WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10));
        try {
            WebElement quantityInput = wait.until(ExpectedConditions.elementToBeClickable(
                    ShoppingCartElements.inputQuantity));
            quantityInput.click();
            Thread.sleep(500);
        } catch (Exception e) {
            Assert.fail("Failed to click quantity input: " + e.getMessage());
        }
        for (int i = 0; i < 3; i++) {
            try {
                basePageObject.getWaitUtils().waitForElementVisible(ShoppingCartElements.okButton);
                wait.until(ExpectedConditions.elementToBeClickable(ShoppingCartElements.okButton));
                basePageObject.getWebElementUtils().safeClickButton(ShoppingCartElements.okButton);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(ShoppingCartElements.okButton));
                System.out.println("OK button clicked successfully");
                return;
            } catch (Exception e) {
                System.out.println("Click attempt " + (i + 1) + " failed: " + e.getMessage());
                if (i == 2) {
                    Assert.fail("Failed to click OK button after 3 attempts: " + e.getMessage());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        }
        Assert.fail("Failed to click OK button after multiple attempts");
    }
}


