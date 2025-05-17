package InternshipProject.Pages;

import InternshipProject.Elements.CheckPageFiltersElements;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class CheckPageFiltersPage {
    BasePageObject basePageObject = new BasePageObject();
    CheckPageFiltersElements checkPageFiltersElements = new CheckPageFiltersElements();
    public CheckPageFiltersPage(){
        PageFactory.initElements(BaseInformation.getDriver(), this);

    }

    public void clickBlack(){
        basePageObject.getWebElementUtils()
                .scrollToElement(checkPageFiltersElements.black);
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(2000, checkPageFiltersElements.black)
                .click();
    }
    public void checkBlueOutline() {
        By productLocator = By.cssSelector("ul.products-grid > li.item");
        WebDriver driver = BaseInformation.getDriver();
        basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(7000, productLocator);
        basePageObject.getWebDriverUtils().waitForPageToLoad();
        int initialProductCount = driver.findElements(productLocator).size();
        for (int i = 0; i < initialProductCount; i++) {
            for (int attempt = 0; attempt < 3; attempt++) {
                try {
                    List<WebElement> currentProducts = driver.findElements(productLocator);
                    if (i >= currentProducts.size()) {
                        break;
                    }
                    WebElement product = currentProducts.get(i);
                    basePageObject.getWebElementUtils().scrollToElement(product);
                    List<WebElement> swatches = product.findElements(By.cssSelector("a.swatch-link.has-image"));
                    boolean hasBlueBorder = swatches.stream()
                            .map(s -> s.getCssValue("border-top-color"))
                            .anyMatch(color -> color.contains("51, 153, 204"));
                    Assert.assertTrue("No blue-bordered swatch found in product " + i, hasBlueBorder);
                    break;
                } catch (StaleElementReferenceException | IndexOutOfBoundsException e) {
                    if (attempt == 2) {
                        System.out.println("Failed to check product " + i + " after 3 attempts: " + e.getMessage());
                    } else {
                        basePageObject.getWaitUtils().waitForSeconds(1);
                    }
                }
            }
        }
    }

    public void navigateBack(){
        BaseInformation.getDriver().navigate().back();
    }
    public void clickPrice(){
        basePageObject.getWebElementUtils().scrollToElement(checkPageFiltersElements.priceRangeLink);
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(2000, checkPageFiltersElements.priceRangeLink)
                .click();

        basePageObject.getWaitUtils()
                .waitForNumberOfElementsToBe(By.cssSelector("ul.products-grid > li.item"), 3, 5000);

        List<WebElement> products = BaseInformation.getDriver()
                .findElements(By.cssSelector("ul.products-grid > li.item"));

        Assert.assertEquals("Expected 3 products to be displayed", 3, products.size());
    }


    public void checkPrices() {
        List<WebElement> products = basePageObject.getWaitUtils()
                .waitForAllElementsVisibleWithCustomTime(5000, By.cssSelector("ul.products-grid > li.item"));

        boolean foundInRange = products.stream().anyMatch(product -> {
            try {
                basePageObject.getWebElementUtils().scrollToElement(product);

                WebElement priceElement = product.findElement(By.cssSelector(".price"));
                String priceText = priceElement.getText().replace("$", "")
                        .replace(",", "").trim();
                double price = Double.parseDouble(priceText);
                return price >= 0 && price <= 99.99;
            } catch (Exception e) {
                return false;
            }
        });

        Assert.assertTrue("No product found in the $0.00 - $99.99 range.", foundInRange);
    }
}
