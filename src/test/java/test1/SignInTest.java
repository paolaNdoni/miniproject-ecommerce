package test1;

import InternshipProject.Pages.RegistrationTestPages.RegisterPage;
import InternshipProject.Pages.RegistrationTestPages.SignInPage;
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
