package test1;

import InternshipProject.Pages.RegistrationTestPages.RegisterPage;
import InternshipProject.Pages.ShopingCartPages.EmptyShoppingCartPage;
import InternshipProject.Pages.ShopingCartPages.ShoppingCartPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ShoppingCartTest extends BaseTest {
    RegisterPage registerPage = new RegisterPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    EmptyShoppingCartPage emptyShoppingCartPage = new EmptyShoppingCartPage();
    @Test()
    public void test() throws InterruptedException {
        shoppingCartPage.clickWishList();
        shoppingCartPage.editProducts();
        registerPage.clickAccountButton();
        shoppingCartPage.clickShoppingCart();
        shoppingCartPage.changeQuantity("2");
        shoppingCartPage.verifyCartTotals();
        emptyShoppingCartPage.removeProductsFromCart();


    }

}
