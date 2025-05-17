package test1;

import InternshipProject.Pages.ShoppingCartPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ShoppingCartTest extends BaseTest {
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    @Test
    public void test(){
        shoppingCartPage.clickWishList();
    }

}
