package InternshipProject.Pages.ModifyElementsPages;

import InternshipProject.Elements.CheckPageFiltersElements;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import static InternshipProject.Elements.CheckPageFiltersElements.productContainer;
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

        basePageObject.getWaitUtils().waitForElementVisibleWithCustomTime(7000, productContainer);
        basePageObject.getWebDriverUtils().waitForPageToLoad();

        List<WebElement> products = BaseInformation.getDriver().findElements(productContainer);
        Assert.assertFalse("No products found on the page", products.isEmpty());

        for (int i = 0; i < products.size(); i++) {
            for (int attempt = 0; true; attempt++) {
                try {
                    List<WebElement> currentProducts = BaseInformation.getDriver().findElements(productContainer);
                    if (i >= currentProducts.size()) {
                        break;
                    }
                    WebElement product = currentProducts.get(i);
                    basePageObject.getWebElementUtils().scrollTo(product);
                    basePageObject.getWaitUtils().waitForSeconds(1);

                    List<WebElement> selectedOptions = product.findElements(CheckPageFiltersElements.selectedOption);
                    if (selectedOptions.isEmpty()) {
                        List<WebElement> allOptions = product.findElements(CheckPageFiltersElements.allOptions);
                        if (!allOptions.isEmpty()) {
                            Assert.fail("Product " +(i+1)+" has "+allOptions.size()+"color options but none are selected");
                        }
                        break;
                    }
                    WebElement selectedOption = selectedOptions.get(0);
                    WebElement swatchLink = selectedOption.findElement(CheckPageFiltersElements.swatchLink);
                    String borderTopColor = swatchLink.getCssValue("border-top-color");
                    boolean hasBlueOutline = borderTopColor.contains("rgb(51, 153, 204, 1)") ||
                            borderTopColor.contains("rgb(51,153,204)");
                    if (!hasBlueOutline) {
                        String borderColor = swatchLink.getCssValue("border-top-color");
                        hasBlueOutline = borderColor.contains("rgba(51, 153, 204, 1)") ||
                                borderColor.contains("rgba(51,153,204)");
                        Assert.assertTrue("Product " + (i+1) + " selected swatch has incorrect border color: " + borderColor,
                                hasBlueOutline);
                    }
                    Assert.assertTrue("No blue border found on selected swatch in product " + (i+1), hasBlueOutline);
                    break;
                } catch (StaleElementReferenceException e) {
                    if (attempt == 2) {
                        Assert.fail("Failed to check product " + (i+1) + " after 3 attempts due to stale elements");
                    }
                    basePageObject.getWaitUtils().waitForSeconds(1);
                } catch (Exception e) {
                    Assert.fail("Error checking product " + (i+1) + ": " + e.getMessage());
                    break;
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
                .waitForNumberOfElementsToBe(productContainer, 3, 5000);

        List<WebElement> products = BaseInformation.getDriver()
                .findElements(productContainer);
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
