package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By zip = By.id("postal-code");
    By continueBtn = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void fillInfo(String fn, String ln, String zc) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(ln);
        wait.until(ExpectedConditions.visibilityOfElementLocated(zip)).sendKeys(zc);
    }

    public void continueCheckout() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));

        btn.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("checkout-step-two"),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))
        ));

        if (!driver.findElements(By.cssSelector("[data-test='error']")).isEmpty()) {
            throw new RuntimeException("Checkout failed: " +
                    driver.findElement(By.cssSelector("[data-test='error']")).getText());
        }
        }
}