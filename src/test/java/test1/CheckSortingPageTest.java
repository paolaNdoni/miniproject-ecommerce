package test1;

import InternshipProject.ConsentCookies.ConsentCookiesPage;
import InternshipProject.Globals.Globals;
import InternshipProject.Pages.ModifyElementsPages.CheckSortingPage;
import InternshipProject.Pages.ModifyElementsPages.HoverStylePage;
import InternshipProject.Pages.RegistrationTestPages.RegisterPage;
import InternshipProject.Pages.RegistrationTestPages.SignInPage;
import InternshipProject.Utilities.BaseInformation;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
//@Listeners(TestListener.class)
public class CheckSortingPageTest  extends BaseTest{
    RegisterPage registerPage = new RegisterPage();
    ConsentCookiesPage consentCookiesPage = new ConsentCookiesPage();
    SignInPage signInPage = new SignInPage();
    HoverStylePage hoverStylePage = new HoverStylePage();
    CheckSortingPage checkSortingPage = new CheckSortingPage();
    @Test(groups = "wishlist" )
    public void test() throws InterruptedException {
        hoverStylePage.hoverWomen();
        hoverStylePage.clickViewAll();
        checkSortingPage.selectPriceDropDown();
        checkSortingPage.checkSortBy();
        checkSortingPage.clickWishlistLinks();
        registerPage.clickAccountButton();
        checkSortingPage.getWishlistNumber(2);
        WebDriver driver = BaseInformation.getDriver();
        System.out.println("Final URL at end of CheckSortingPageTest: " + driver.getCurrentUrl());

    }
}
