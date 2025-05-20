package miniprojectTest;

import InternshipProject.ConsentCookies.ConsentCookiesPage;
import InternshipProject.Globals.Globals;
import InternshipProject.Pages.RegistrationTestPages.RegisterPage;

import miniprojectTest.Listeners.ExtentTestListener;
import miniprojectTest.Listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({TestListener.class, ExtentTestListener.class})
public class RegistrationTest extends BaseTest {
    RegisterPage registerPage = new RegisterPage();
    ConsentCookiesPage consentCookiesPage = new ConsentCookiesPage();

    @Test
    public void test(){
        registerPage.getUrl(Globals.baseUrl);
        consentCookiesPage.acceptCookiesButton();
        consentCookiesPage.submitButton();
        registerPage.clickAccountButton();
        registerPage.clickRegisterButton();
        registerPage.checkPageTitle("Tealium Ecommerce Demo");
        registerPage.setFirstName("Paola");
        registerPage.setMiddleName("Pao");
        registerPage.setLastName("Ndoni");
        registerPage.setEmail();
        registerPage.setPassword();
        registerPage.setConfirmPassword();
        registerPage.clickRegisterButtonForm();
        registerPage.checkRegister();
        registerPage.clickAccountButton();
        registerPage.clickLogOut();

    }
}
