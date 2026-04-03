package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTest extends BaseTest {

        @Test
        public  void testOneItemCheckout(){

                LoginPage login = new LoginPage(driver);
                ProductsPage products = new ProductsPage(driver);
                CartPage cart = new CartPage(driver);
                CheckoutPage checkout = new CheckoutPage(driver);
                OverviewPage overview = new OverviewPage(driver);
                SuccessPage success = new SuccessPage(driver);

                login.login("standard_user", "secret_sauce");

                products.addProduct("Sauce Labs Backpack");
                products.goToCart();

                Assert.assertTrue(cart.isProductAvailable("Sauce Labs Backpack"));

                cart.checkout();

                checkout.fillInfo("ABC", "Test", "10000");
                checkout.continueCheckout();

                overview.clickFinish();

                Assert.assertEquals(success.getSuccessMsg(), "Thank you for your order!");

        }

        @Test
        public void testMultipleItemsCheckout() {

                LoginPage login = new LoginPage(driver);
                ProductsPage products = new ProductsPage(driver);
                CartPage cart = new CartPage(driver);
                CheckoutPage checkout = new CheckoutPage(driver);
                OverviewPage overview = new OverviewPage(driver);
                SuccessPage success = new SuccessPage(driver);

                login.login("standard_user", "secret_sauce");

                products.addProduct("Sauce Labs Backpack");
                products.addProduct("Sauce Labs Onesie");
                products.goToCart();


            cart.checkout();

                checkout.fillInfo("Alex", "D", "12345");
                checkout.continueCheckout();

                Assert.assertEquals(
                        overview.getItemTotalLabel(),
                        overview.getItemsTotal(),
                        "Item total mismatch!"
                );


                double itemTotal = overview.getItemsTotal();
                double tax = overview.getTax();
                double expectedTotal = itemTotal + tax;

                Assert.assertEquals(
                        overview.getFinalTotal(),
                        expectedTotal,
                        0.01,
                        "Final price mismatch!"
                );

                overview.clickFinish();

                Assert.assertEquals(success.getSuccessMsg(), "Thank you for your order!");
        }



}
