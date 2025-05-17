package Other.PROJECT.pages;

import Other.PROJECT.elements.RegisterPageElements;
import Other.PROJECT.utilities.BaseInformation;
import Other.PROJECT.utilities.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Other.PROJECT.utilities.BaseInformation.driver;


public class RegisterPage {
    BasePageObject basePageObject = new BasePageObject(BaseInformation.getBaseInformation());
    RegisterPageElements registerPageElements =  new RegisterPageElements();

    public RegisterPage(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    public void getUrl(String url){
        BaseInformation.getDriver().get(url);
    }
    public void clickAccountButton(){
        try {

            By[] accountButtonLocators = {
                    By.xpath("//a[@class='skip-link skip-account']"),
                    By.cssSelector("a.skip-link.skip-account"),
                    By.xpath("//a[contains(@class,'account') and contains(text(),'Account')]"),
                    By.cssSelector("div.skip-links > a:first-child")
            };

            for (By locator : accountButtonLocators) {
                try {
                    basePageObject.getWaitUtils()
                                    .waitForElementClickable(registerPageElements.accountButton)
                            .click();
                    return;
                } catch (Exception e) {
                    System.out.println("Attempt failed with locator: " + locator);
                }
            }
            throw new NoSuchElementException("Could not find account button with any locator");
        } catch (Exception e) {
            takeScreenshot("account_button_error");
            throw e;
        }
    }
    private void takeScreenshot(String name) {
        try {
            Path directory = Paths.get("screenshots");
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            Path destination = directory.resolve(name + ".png");
            byte[] screenshotBytes = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            Files.write(destination, screenshotBytes);

            System.out.println("Screenshot saved to: " + destination.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
    }
    public void clickRegisterButton(){
        basePageObject.getWaitUtils()
                .waitForElementClickable(registerPageElements.registerButton)
                .click();
    }
    public String getTitle() {
        return driver.getTitle();
    }

    public void setFirstName(String firstName) {
        basePageObject.getWebElementUtils()
                .sendKeysToElementWithWait(registerPageElements.firstNameField, firstName, 1000);
    }
}

