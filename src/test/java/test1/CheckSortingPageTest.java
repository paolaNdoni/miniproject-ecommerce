package test1;

import InternshipProject.Pages.ModifyElementsPages.CheckSortingPage;
import InternshipProject.Pages.ModifyElementsPages.HoverStylePage;
import InternshipProject.Pages.RegistrationTestPages.RegisterPage;

import org.testng.annotations.Test;
//@Listeners(TestListener.class)
public class CheckSortingPageTest  extends BaseTest{
    RegisterPage registerPage = new RegisterPage();
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
    }
}
