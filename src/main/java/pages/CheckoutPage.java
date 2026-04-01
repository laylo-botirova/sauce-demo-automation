package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By zip = By.id("postal-code");
    By continueBtn = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillInfo(String fn, String ln, String zc) {
        WebElement fnEl = wait.until(ExpectedConditions.elementToBeClickable(firstName));
        fnEl.clear();
        fnEl.sendKeys(fn);

        WebElement lnEl = wait.until(ExpectedConditions.elementToBeClickable(lastName));
        lnEl.clear();
        lnEl.sendKeys(ln);

        WebElement zipEl = wait.until(ExpectedConditions.elementToBeClickable(zip));
        zipEl.clear();
        zipEl.sendKeys(zc);
    }

    public void continueCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
    }
}