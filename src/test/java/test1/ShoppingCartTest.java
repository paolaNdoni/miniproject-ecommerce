package test1;

import InternshipProject.ConsentCookies.ConsentCookiesPage;
import InternshipProject.Globals.Globals;
import InternshipProject.Pages.RegistrationTestPages.RegisterPage;
import InternshipProject.Pages.ShopingCartPages.ShoppingCartPage;
import InternshipProject.Pages.RegistrationTestPages.SignInPage;
import org.testng.annotations.Test;

//@Listeners(TestListener.class)
public class ShoppingCartTest extends BaseTest {

    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    @Test()
    public void test() throws InterruptedException {
        shoppingCartPage.clickWishList();
        shoppingCartPage.editProducts();
        Thread.sleep(2000);
        shoppingCartPage.clickShoppingCart();
        shoppingCartPage.changeQuantity("2");
        shoppingCartPage.clickOk();

    }

}
