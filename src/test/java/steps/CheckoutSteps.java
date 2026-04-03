package steps;

import base.DriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;

public class CheckoutSteps {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OverviewPage overviewPage;

    @Given("user is logged in")
    public void login(){
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }


    @When("user adds product {string} to cart")
    public void addSingleProduct(String productName){
        productsPage = new ProductsPage(driver);
        productsPage.addProduct(productName);
        productsPage.goToCart();
    }

    @When("user adds products to cart")
    public void addMultipleProducts(DataTable table){
        productsPage = new ProductsPage(driver);

        for (String product : table.asList()) {
            productsPage.addProduct(product);
        }

        productsPage.goToCart();
    }

    @When("user clicks checkout")
    public void clickCheckout(){
        cartPage = new CartPage(driver);
        cartPage.checkout();
    }

    @When("user enters checkout information")
    public void fillInfo(){
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillInfo("Alex","Smith","10000");
        checkoutPage.continueCheckout();
    }

    @Then("user should validate total price")
    public void validateTotalPrice(){
        overviewPage = new OverviewPage(driver);

        double itemTotal = overviewPage.getItemsTotal();
        double tax = overviewPage.getTax();
        double expectedTotal = itemTotal + tax;

        Assert.assertEquals(
                overviewPage.getFinalTotal(),
                expectedTotal,
                0.01,
                "Final price mismatch!"
        );
    }

    @Then("user should complete the order")
    public void finishOrder(){
        overviewPage = new OverviewPage(driver);
        overviewPage.clickFinish();
    }


}
