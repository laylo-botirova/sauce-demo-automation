package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OverviewPage {
    WebDriver driver;
    WebDriverWait wait;

    By itemPrices = By.className("inventory_item_price");
    By totalPrice = By.className("summary_total_label");
    By finishBtn = By.id("finish");
    By itemTotalLabel = By.className("summary_subtotal_label");
    By taxLabel = By.className("summary_tax_label");

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public double getItemsTotal() {
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(itemPrices, 0));        List<WebElement> prices = driver.findElements(itemPrices);
        double sum = 0;
        for (WebElement price : prices) {
            sum += Double.parseDouble(price.getText().replace("$", ""));
        }
        return sum;
    }

    public double getItemTotalLabel() {
        String text = driver.findElement(itemTotalLabel).getText();
        return Double.parseDouble(text.replace("Item total: $", ""));
    }

    public double getTax() {
        String text = driver.findElement(taxLabel).getText();
        return Double.parseDouble(text.replace("Tax: $", ""));
    }

    public double getFinalTotal() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice)).getText();
        return Double.parseDouble(text.replace("Total: $", ""));
    }

    public void clickFinish() {
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(finishBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }
}