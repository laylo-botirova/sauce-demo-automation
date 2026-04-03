package steps;

import base.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverFactory;

public class Hooks {

    @Before
    public void setup(io.cucumber.java.Scenario scenario) {
        String browser = System.getProperty("browser");

        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.get("browser");
        }

        WebDriver driver = DriverFactory.getDriver(browser);

        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseUrl"));

        DriverManager.setDriver(driver);
    }

    @After
    public void teardown() {
        DriverManager.quitDriver();
    }
}
