package test1;

import InternshipProject.Pages.CheckSortingPage;
import InternshipProject.Pages.HoverStylePage;
import InternshipProject.Pages.RegisterPage;
import InternshipProject.Utilities.BaseInformation;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(TestListener.class)
public class CheckSortingPageTest  extends BaseTest{
    HoverStylePage hoverStylePage = new HoverStylePage();
    CheckSortingPage checkSortingPage = new CheckSortingPage();
    RegisterPage registerPage = new RegisterPage();
    @Test
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
