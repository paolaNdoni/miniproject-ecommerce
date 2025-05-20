package miniprojectTest;

import InternshipProject.ConsentCookies.ConsentCookiesPage;
import InternshipProject.Globals.Globals;
import InternshipProject.Pages.ModifyElementsPages.HoverStylePage;
import InternshipProject.Pages.RegistrationTestPages.RegisterPage;
import InternshipProject.Pages.RegistrationTestPages.SignInPage;
import miniprojectTest.Listeners.ExtentTestListener;
import miniprojectTest.Listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({TestListener.class , ExtentTestListener.class})
public class HoverStyleTest extends BaseTest {
    ConsentCookiesPage consentCookiesPage = new ConsentCookiesPage();
    RegisterPage registerPage = new RegisterPage();
    SignInPage signInPage = new SignInPage();
    HoverStylePage hoverStylePage = new HoverStylePage();

    @Test
    public void test (){
        registerPage.getUrl(Globals.baseUrl);
        consentCookiesPage.acceptCookiesButton();
        consentCookiesPage.submitButton();
        registerPage.clickAccountButton();
        signInPage.login();
        hoverStylePage.hoverWomen();
        hoverStylePage.clickViewAll();
        hoverStylePage.hoverDress();

    }
}

