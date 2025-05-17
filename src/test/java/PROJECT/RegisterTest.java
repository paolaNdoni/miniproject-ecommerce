package PROJECT;

import MiniProject.CookieConsent.CookieConsentPage;
import MiniProject.Globals.Globals;
import MiniProject.Pages.SignInPage;
import MiniProject.Utilities.BaseInformation;
import MiniProject.Utilities.WebDriverUtils;
import MiniProject.Pages.RegisterPage;
import org.testng.annotations.*;

import static MiniProject.Utilities.BaseInformation.driver;


public class RegisterTest {

    @BeforeClass
    public void setup() {
        WebDriverUtils.maximizeWindowOfDriver(BaseInformation.getBaseInformation());
        signInPage.getUrl(Globals.baseUrl);
    }
    CookieConsentPage cookieConsentPage = new CookieConsentPage(driver);
    SignInPage signInPage = new SignInPage();
    RegisterPage registerPage = new RegisterPage();

    @Test
    public void registerUserTest() {
        cookieConsentPage.acceptCookies();
        registerPage.navigateToAccount();
        registerPage.navigateToRegister();
        registerPage.setFirstName("Paola");
        registerPage.setMiddleName("P");
        registerPage.setLastName("Ndoni");
        registerPage.setEmail();
        registerPage.setPassword();
        registerPage.setConfirmPassword();
//        registerPage.clickRegister();
//        registerPage.checkSuccessMessage();
//        registerPage.navigateToAccount();
//        registerPage.clickLogOut();
    }
    @AfterClass
    public void tearDown() {
        BaseInformation.quit();
    }

}
