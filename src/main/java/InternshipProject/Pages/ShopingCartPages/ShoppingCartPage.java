package InternshipProject.Pages.ShopingCartPages;

import InternshipProject.Elements.RegisterPageElements;
import InternshipProject.Elements.ShoppingCartElements;
import InternshipProject.Elements.WishlistElements;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
    public void clickAccount(){
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
        WebDriver driver = BaseInformation.getDriver();
        basePageObject.getWaitUtils().waitForPageToLoad();
        int editsRemaining = 2;
        int processed = 0;
        int attempts = 0;
        int maxAttempts = 2;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (processed < editsRemaining && attempts < maxAttempts) {
            try {
                if (attempts > 0) {
                    driver.navigate().refresh();
                    basePageObject.getWaitUtils().waitForPageToLoad();
                    Thread.sleep(1000);
                }
                List<WebElement> productRows = driver.findElements(By.cssSelector("tbody tr"));
                if (productRows.isEmpty()) {
                    System.out.println("No products found in wishlist, retrying...");
                    attempts++;
                    continue;
                }
                WebElement currentRow = productRows.get(0);
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", currentRow);
                Thread.sleep(500);

                WebElement editButton = basePageObject.getWebElementUtils().findClickableElement(
                        driver, By.cssSelector("a.link-edit"));
                if (editButton == null) {
                    System.out.println("Edit button not found, retrying...");
                    attempts++;
                    continue;
                }

                js.executeScript("arguments[0].click();", editButton);
                basePageObject.getWaitUtils().waitForPageToLoad();
                WebElement sizeOption = basePageObject.getWebElementUtils().findClickableElement(driver, By.xpath("//span[contains(text(),' S ')]"));
                if (sizeOption == null) {
                    System.out.println("Size option not found, retrying...");
                    attempts++;
                    continue;
                }
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", sizeOption);
                Thread.sleep(500);
                js.executeScript("arguments[0].click();", sizeOption);
                String color = (processed == 0) ? "Black" : "White";
                WebElement colorOption = basePageObject.getWebElementUtils()
                        .findClickableElement(driver, By.cssSelector("img[alt='" + color + "']"));
                if (colorOption == null) {
                    System.out.println("Color option not found, retrying...");
                    attempts++;
                    continue;
                }
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", colorOption);
                Thread.sleep(500);
                js.executeScript("arguments[0].click();", colorOption);
                WebElement addToCart = basePageObject.getWebElementUtils().findClickableElement(driver, By.xpath("//button[contains(@onclick,'submit')]"));
                if (addToCart == null) {
                    System.out.println("Add to cart button not found, retrying...");
                    attempts++;
                    continue;
                }
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", addToCart);
                Thread.sleep(500);
                js.executeScript("arguments[0].click();", addToCart);
                basePageObject.getWaitUtils().waitForPageToLoad();

                processed++;
                attempts = 0;
                System.out.println("Successfully processed product " + processed + " of " + editsRemaining);
                navigateToWishlist(driver);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                attempts++;
                try {
                    navigateToWishlist(driver);
                } catch (Exception recoveryEx) {
                    System.out.println("Recovery failed: " + recoveryEx.getMessage());
                }
            }
        }

        if (processed < editsRemaining) {
            System.out.println("Only completed " + processed + " edits out of " + editsRemaining);
        }
    }

    private void navigateToWishlist(WebDriver driver){
        for (int i = 0; i < 3; i++) {
            try {
                clickAccount();
                Thread.sleep(500);
                clickWishList();
                basePageObject.getWaitUtils().waitForPageToLoad();
                if (!driver.findElements(By.cssSelector("tbody tr")).isEmpty()) {
                    return;
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                //
            }
        }
        throw new RuntimeException("Failed to navigate to wishlist");
    }

    public void clickShoppingCart() {
        WebElement cart = find(By.xpath("//a[contains(@href,'cart')]"));
        basePageObject.getWebElementUtils().scrollToElement(cart);
        cart.click();
    }

    public void changeQuantity(String qtyText) {
        By locator = By.cssSelector("input[id^='qinput-']");
        WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) BaseInformation.getDriver();

        for (int i = 0; i < 3; i++) {
            try {
                WebElement qty = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", qty);
                Thread.sleep(500);
                js.executeScript(
                        "arguments[0].value = arguments[1]; " +
                                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                        qty, qtyText
                );
                Thread.sleep(500);
                String currentValue = qty.getAttribute("value");

                if (qtyText.equals(currentValue)) {
                    System.out.println("Quantity changed to: " + qtyText);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Attempt " + (i+1) + " failed: " + e.getMessage());
            }
        }
        throw new RuntimeException("Failed to change quantity after multiple attempts");
    }

    public void clickOk() {
        WebDriver driver = BaseInformation.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            WebElement quantityInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("input[id^='qinput-']")));
            quantityInput.click();
            Thread.sleep(300);
        } catch (Exception e) {
            System.out.println("Note: Could not click quantity input first: " + e.getMessage());
        }
        By okButtonLocator = By.cssSelector("[id^='qbutton-'], .confirm-button, .ok-button");
        for (int i = 0; i < 3; i++) {
            try {
                WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(okButtonLocator));
                try {
                    okButton.click();
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", okButton);
                }
                wait.until(ExpectedConditions.invisibilityOfElementLocated(okButtonLocator));
                return;
            } catch (Exception e) {
                System.out.println("Click attempt " + (i+1) + " failed: " + e.getMessage());
                try { Thread.sleep(500); } catch (InterruptedException ie) { /* ignore */ }
            }
        }
        throw new RuntimeException("Failed to click OK button after multiple attempts");
    }
}


