package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {
    WebDriver driver;
    WebDriverWait wait;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addProduct(String productName) {
        String xpath = "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button";
        By locator = By.xpath(xpath);

        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );

        wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
    }

    public void goToCart() {
        By cartLocator = By.className("shopping_cart_link");

        WebElement cartBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartLocator));

        wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", cartBtn
        );

        wait.until(ExpectedConditions.urlContains("cart"));
    }
}
