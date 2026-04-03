package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        wait.until(driver ->
                driver.getCurrentUrl().contains("checkout-step-two") ||
                        driver.getCurrentUrl().contains("checkout-step-one")
        );

        System.out.println("Current URL after continue: " + driver.getCurrentUrl());
    }
}