package miniprojectTest;

import InternshipProject.Pages.ModifyElementsPages.CheckPageFiltersPage;
import InternshipProject.Pages.ModifyElementsPages.HoverStylePage;
import miniprojectTest.Listeners.ExtentTestListener;
import miniprojectTest.Listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class , ExtentTestListener.class})
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
