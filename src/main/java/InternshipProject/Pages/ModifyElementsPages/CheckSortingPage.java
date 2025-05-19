package InternshipProject.Pages.ModifyElementsPages;

import InternshipProject.Elements.CheckSortByElements;
import InternshipProject.Elements.WishlistElements;
import InternshipProject.Pages.RegistrationTestPages.SignInPage;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CheckSortingPage {
    BasePageObject basePageObject = new BasePageObject();
    WishlistElements wishlistElements = new WishlistElements();
    SignInPage signInPage = new SignInPage();
    CheckSortByElements checkSortByElements = new CheckSortByElements();
    private void ensureUserLoggedIn() {
        if (!isUserLoggedIn()) {
            // Use your existing SigningIn class
            signInPage.login(); // Or whatever your login method is called

            basePageObject.getWaitUtils().waitForPageToLoad();

            // Verify login was successful
            if (!isUserLoggedIn()) {
                throw new RuntimeException("Failed to login before test execution");
            }
        }
    }
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
        WebDriverWait wait = new WebDriverWait(BaseInformation.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(checkSortByElements.items));
        List<Double> prices = new ArrayList<>();
        JavascriptExecutor js = (JavascriptExecutor) BaseInformation.getDriver();
        try {
            String priceScript =
                    "return Array.from(arguments[0]).map(function(item) {" +
                            "  let priceEl = item.querySelector('.special-price .price') || item.querySelector('.regular-price .price');" +
                            "  if (!priceEl) return null;" +
                            "  let priceText = priceEl.innerText.replace(/[^0-9.,]/g, '').replace(',', '');" +
                            "  return priceText;" +
                            "});";
            List<String> priceTexts = (List<String>) js.executeScript(priceScript, checkSortByElements.items);
            for (String priceText : priceTexts) {
                if (priceText != null && !priceText.isEmpty()) {
                    try {
                        prices.add(Double.parseDouble(priceText));
                    } catch (NumberFormatException e) {
                        System.out.println("Warning: Could not parse price: " + priceText);
                    }
                }
            }
            if (prices.isEmpty()) {
                Assert.fail("Failed to extract any product prices");
            }
            boolean isSorted = true;
            String errorMessage = "Products are not sorted by price in ascending order";
            for (int i = 0; i < prices.size() - 1; i++) {
                if (prices.get(i) > prices.get(i + 1)) {
                    isSorted = false;
                    errorMessage += ". Found " + prices.get(i) + " before " + prices.get(i + 1);
                    break;
                }
            }

            Assert.assertTrue(errorMessage, isSorted);
            System.out.println("Verified: Products are correctly sorted from cheapest to most expensive");
        } catch (Exception e) {
            Assert.fail("Error checking sort order: " + e.getMessage());
        }
    }

    public void clickWishlistLinks() {
        WebDriver driver = BaseInformation.getDriver();
        basePageObject.getWebElementUtils().scrollUp(300);
        String originalUrl = driver.getCurrentUrl();
        for (int i = 0; i < 2; i++) {
            assert originalUrl != null;
            driver.get(originalUrl);
            basePageObject.getWaitUtils().waitForPageToLoad();
            WebElement wishlistContainer = driver.findElement(By.xpath("//ul[@class='add-to-links']"));
            basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(
                    10000, wishlistContainer);
            basePageObject.getWaitUtils().waitForAllElementsVisible(checkSortByElements.items);
            List<WebElement> wishlistLinks = driver.findElements(By.cssSelector("a[class='link-wishlist']"));
            if (wishlistLinks.size() <= i) {
                throw new RuntimeException("Not enough wishlist items to click. Expected at least " + (i + 1));
            }
            basePageObject.getWaitUtils().waitForSeconds(1);
            WebElement wishlistLink = wishlistLinks.get(i);
            basePageObject.getWebElementUtils().scrollToElement(wishlistLink);
            basePageObject.getWaitUtils().waitForElementClickable(wishlistLink);
            try {
                wishlistLink.click();
            } catch (StaleElementReferenceException e) {
                wishlistLinks = driver.findElements(By.cssSelector("a[class='link-wishlist']"));
                if (wishlistLinks.size() <= i) {
                    throw new RuntimeException("Element became stale and not enough wishlist items after refresh");
                }
                wishlistLink = wishlistLinks.get(i);
                wishlistLink.click();
            }
            basePageObject.getWaitUtils().waitForElementVisibleFluently(
                    By.xpath("//h1[text()='My Wishlist']"), 7, 500);
            basePageObject.getWaitUtils().waitForPageToLoad();

            if (!isUserLoggedIn()) {
                driver.navigate().refresh();
                basePageObject.getWaitUtils().waitForPageToLoad();
            if (!isUserLoggedIn()) {
                throw new RuntimeException("User got logged out after clicking wishlist link at index " + i);
            }
            }
        }
    }

    private boolean isUserLoggedIn() {
        return !BaseInformation.getDriver().findElements(By.cssSelector("a[href*='logout']")).isEmpty();
    }

    public void getWishlistNumber(int expectedCount) {
        WebDriver driver = BaseInformation.getDriver();
        basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(20000, wishlistElements.wishlist);
        WebElement wishlistNumberElement = driver.findElement(By.cssSelector("a[href*='wishlist']"));
        if (!isUserLoggedIn()) {
            driver.navigate().refresh();
            basePageObject.getWaitUtils().waitForPageToLoad();
            wishlistNumberElement = driver.findElement(By.cssSelector("a[href*='wishlist']"));
        }

        String text = wishlistNumberElement.getText();
        System.out.println("Wishlist text: " + text);

        try {
            int start = text.indexOf('(');
            int end = text.indexOf(')');
            if (start != -1 && end != -1 && start < end) {
                String numberStr = text.substring(start + 1, end).trim().split(" ")[0];
                int actualCount = Integer.parseInt(numberStr);
                Assert.assertEquals("Wishlist count incorrect", expectedCount, actualCount);
            } else {
                throw new RuntimeException("Wishlist item count not found in the link text: " + text);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse wishlist count: " + text, e);
        }
    }


}







