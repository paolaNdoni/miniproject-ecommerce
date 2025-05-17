package test1;

import InternshipProject.Globals.Globals;
import InternshipProject.Pages.RegisterPage;
import InternshipProject.Pages.SignInPage;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.ConsentCookies.ConsentCookiesPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class SignInTest extends BaseTest {
    RegisterPage registerPage = new RegisterPage();
    SignInPage signInPage = new SignInPage();

    @Test
    public void test() {
        signInPage.deleteCookies();
        registerPage.clickAccountButton();
        signInPage.login();
        signInPage.checkMessage();
        registerPage.clickAccountButton();
        signInPage.clickLogOut();

    }


}
