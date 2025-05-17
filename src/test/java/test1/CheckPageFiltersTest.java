package test1;

import InternshipProject.ConsentCookies.ConsentCookiesPage;
import InternshipProject.Globals.Globals;
import InternshipProject.Pages.CheckPageFiltersPage;
import InternshipProject.Pages.HoverStylePage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class CheckPageFiltersTest extends BaseTest{
    HoverStylePage hoverStylePage = new HoverStylePage();
    CheckPageFiltersPage checkPageFiltersPage = new CheckPageFiltersPage();
    @Test
    public void test() throws InterruptedException {

        hoverStylePage.hoverMan();
        hoverStylePage.clickViewAllMen();
        checkPageFiltersPage.clickBlack();
        checkPageFiltersPage.checkBlueOutline();
        checkPageFiltersPage.navigateBack();
        checkPageFiltersPage.clickPrice();
        checkPageFiltersPage.checkPrices();
    }
}
