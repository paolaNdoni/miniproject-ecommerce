package test1;


import InternshipProject.Pages.CheckSalePage;
import InternshipProject.Pages.HoverStylePage;

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
