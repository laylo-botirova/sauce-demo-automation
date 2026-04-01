package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessPage {
    WebDriver driver;

    public SuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    By successMsg = By.className("complete-header");


    public String getSuccessMsg() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).getText();
    }
}
