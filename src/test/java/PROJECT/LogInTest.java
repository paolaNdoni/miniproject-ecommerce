package PROJECT;

import MiniProject.CookieConsent.CookieConsentPage;
import MiniProject.Globals.Globals;
import MiniProject.Pages.RegisterPage;
import MiniProject.Pages.SignInPage;
import MiniProject.Utilities.BaseInformation;
import MiniProject.Utilities.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogInTest {
    WebDriver driver;
    BaseInformation baseInformation;
    RegisterPage registerPage;
    CookieConsentPage cookieConsentPage;
    SignInPage signInPage;
    @BeforeClass
    public void setup() {
        baseInformation = MiniProject.Utilities.BaseInformation.getBaseInformation();
        driver = MiniProject.Utilities.BaseInformation.getDriver();
        WebDriverUtils.maximizeWindowOfDriver(baseInformation);
        registerPage = new RegisterPage();
        signInPage.getUrl(Globals.baseUrl);
        cookieConsentPage = new CookieConsentPage(driver);
    }
    @Test
    public void logInTest() {
        cookieConsentPage.acceptCookies();
        signInPage.clickAccount();
        signInPage.clickLogIn();
        signInPage.setEmail();
        signInPage.setPassword();
    }

}
