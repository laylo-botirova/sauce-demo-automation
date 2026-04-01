package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By checkoutBtn = By.id("checkout");

        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(checkoutBtn));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );

        WebElement btnClickable = wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnClickable);        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
    }
    public boolean isProductAvailable(String productName) {
        return !driver.findElements(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']")
        ).isEmpty();
    }
}
