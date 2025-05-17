package Other.PROJECT.utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;



import java.time.Duration;
public class ConsentHandler {
    private WebDriver driver;
    private WebDriverWait wait;

    public ConsentHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Increased wait time
    }

    public void acceptConsentIfPresent() {
        try {
            By consentButton = By.id("privacy_pref_optin");

            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(consentButton));

            if (button != null && button.isDisplayed()) {
                button.click();
                System.out.println("Consent accepted successfully.");
            } else {
                System.out.println("Consent button not found or not visible.");
            }

            By submitButton = By.id("consent_prompt_submit");
            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
            submitBtn.click();
            System.out.println("Submit button clicked successfully.");

            By consentPopup = By.id("cookie-consent-popup");  // Replace with the actual consent popup ID
            wait.until(ExpectedConditions.invisibilityOfElementLocated(consentPopup));
            System.out.println("Consent popup closed.");

        } catch (TimeoutException e) {
            System.out.println("Consent popup not displayed — proceeding.");
        } catch (NoSuchElementException e) {
            System.out.println("Consent element not found.");
        } catch (StaleElementReferenceException e) {
            System.out.println("Element became stale, trying again...");
        }
    }
}