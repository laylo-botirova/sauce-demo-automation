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
        WebElement first = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        WebElement last = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
        WebElement postal = wait.until(ExpectedConditions.visibilityOfElementLocated(zip));

        first.clear();
        first.sendKeys(fn);

        last.clear();
        last.sendKeys(ln);

        postal.clear();
        postal.sendKeys(zc);

        wait.until(d -> first.getAttribute("value").equals(fn));
        wait.until(d -> last.getAttribute("value").equals(ln));
        wait.until(d -> postal.getAttribute("value").equals(zc));
    }

    public void continueCheckout() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        System.out.println("URL before click: " + driver.getCurrentUrl());
        btn.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("checkout-step-two"),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))
        ));

        System.out.println("URL after click: " + driver.getCurrentUrl());

        if (!driver.findElements(By.cssSelector("[data-test='error']")).isEmpty()) {
            String error = driver.findElement(By.cssSelector("[data-test='error']")).getText();
            System.out.println("Checkout ERROR: " + error);
            throw new RuntimeException("Checkout failed: " + error);
        }
        }
}