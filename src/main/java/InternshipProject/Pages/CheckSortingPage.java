package InternshipProject.Pages;

import InternshipProject.Elements.CheckSortByElements;
import InternshipProject.Elements.HoverStyleElements;
import InternshipProject.Elements.WishlistElements;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class CheckSortingPage {
    BasePageObject basePageObject = new BasePageObject();
    WishlistElements wishlistElements = new WishlistElements();
    CheckSortByElements checkSortByElements = new CheckSortByElements();

    public CheckSortingPage() {
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }

    public void selectPriceDropDown() {
        By sortByLocator = By.xpath("//select[@title='Sort By']");
        basePageObject.getWebDriverUtils().waitForPageToLoad();
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement sortByDropdown = basePageObject.getWaitUtils()
                        .waitForElementVisibleWithCustomTime(10000, sortByLocator);
                basePageObject.getWebElementUtils().scrollToElement(sortByDropdown);
                basePageObject.getWaitUtils().waitForElementClickable(sortByDropdown);
                Select select = new Select(sortByDropdown);
                select.selectByVisibleText("Price");
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 3) {
                    throw new RuntimeException("Failed to select 'Price' from dropdown due to stale element", e);
                }
            }
        }
    }




    public void checkSortBy() {

        basePageObject.getWaitUtils().waitForAllElementsVisible(checkSortByElements.items);
        List<Double> prices = new ArrayList<>();
        for (WebElement product : checkSortByElements.items) {
            try {
                basePageObject.getWebElementUtils().scrollToElement(product);
                WebElement priceEl;
                try {
                    priceEl = product.findElement(By.cssSelector(".special-price .price"));
                } catch (NoSuchElementException e) {
                    priceEl = product.findElement(By.cssSelector(".regular-price .price"));
                }
                String priceText = priceEl.getText().replaceAll("[^0-9.,]", "").replace(",", "");
                double price = Double.parseDouble(priceText);
                prices.add(price);
            } catch (Exception e) {
                Assert.fail("Failed to get price for a product: " + e.getMessage());
            }
        }
    }

    public void clickWishlistLinks() {
        WebDriver driver = BaseInformation.getDriver();
        basePageObject.getWebElementUtils().scrollUp(300);
        String originalUrl = driver.getCurrentUrl();
        for (int i = 0; i < 2; i++) {
            List<WebElement> wishlistLinks = driver.findElements(By.cssSelector("a[class='link-wishlist']"));
            if (wishlistLinks.size() <= i) {
                throw new RuntimeException("Not enough wishlist items to click. Expected at least " + (i + 1));
            }
            WebElement wishlistLink = wishlistLinks.get(i);
            basePageObject.getWebElementUtils().scrollToElement(wishlistLink);
            basePageObject.getWaitUtils().waitForElementClickable(wishlistLink);
            wishlistLink.click();
            basePageObject.getWaitUtils().waitForElementVisibleFluently(
                    By.xpath("//h1[text()='My Wishlist']"), 7, 500);
            basePageObject.getWaitUtils().waitForPageToLoad();
            if (!isUserLoggedIn()) {
                throw new RuntimeException("User got logged out after clicking wishlist link at index " + i);
            }
            driver.get(originalUrl);
            basePageObject.getWaitUtils().waitForPageToLoad();
            basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(
                    10000, wishlistElements.wishListContainer);
            basePageObject.getWaitUtils().waitForAllElementsVisible(checkSortByElements.items);
            basePageObject.getWaitUtils().waitForSeconds(1);
        }
    }


    private boolean isUserLoggedIn() {
        return !BaseInformation.getDriver().findElements(By.cssSelector("a[href*='logout']")).isEmpty();
    }

    public void getWishlistNumber(int expectedCount) {
        basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(20000, wishlistElements.wishlist);

        WebElement wishlistNumberElement = BaseInformation.getDriver().findElement(By.cssSelector("a[href*='wishlist']"));
        String text = wishlistNumberElement.getText();

        try {
            int start = text.indexOf('(');
            int end = text.indexOf(')');
            if (start != -1 && end != -1 && start < end) {
                String numberStr = text.substring(start + 1, end).trim().split(" ")[0];
                int actualCount = Integer.parseInt(numberStr);
                Assert.assertEquals(String.valueOf(actualCount), 2,
                        expectedCount);
            } else {
                throw new RuntimeException("Wishlist item count not found in the link text: " + text);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse wishlist count: " + text, e);
        }
    }


}







