package miniprojectTest;


import InternshipProject.Pages.ModifyElementsPages.CheckSalePage;
import InternshipProject.Pages.ModifyElementsPages.HoverStylePage;

import miniprojectTest.Listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(TestListener.class)
public class CheckSaleTest extends BaseTest {

    HoverStylePage hoverStylePage = new HoverStylePage();
    CheckSalePage checkSalePage = new CheckSalePage();
    @Test
    public void test() throws InterruptedException {

        hoverStylePage.hoverSale();
        hoverStylePage.clickViewAllSale();
        checkSalePage.checkDiscount();
        checkSalePage.verifyRegularColor();
        checkSalePage.verifySaleColor();
    }
}
