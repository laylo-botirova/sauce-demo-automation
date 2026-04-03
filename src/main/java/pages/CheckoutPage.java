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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillInfo(String fn, String ln, String zc) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(fn);

        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(ln);

        driver.findElement(zip).clear();
        driver.findElement(zip).sendKeys(zc);

        driver.findElement(zip).sendKeys("\t");

        wait.until(driver ->
                driver.findElement(firstName).getAttribute("value").equals(fn) &&
                        driver.findElement(lastName).getAttribute("value").equals(ln) &&
                        driver.findElement(zip).getAttribute("value").equals(zc)
        );
    }

    public void continueCheckout() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));

        btn.click();

        By error = By.cssSelector("[data-test='error']");

        wait.until(driver ->
                driver.getCurrentUrl().contains("checkout-step-two") ||
                        driver.findElements(error).size() > 0
        );

        if (driver.findElements(error).size() > 0) {
            String errorMsg = driver.findElement(error).getText();
            throw new RuntimeException("Checkout failed: " + errorMsg);
        }

        System.out.println("Current URL after continue: " + driver.getCurrentUrl());
    }
}