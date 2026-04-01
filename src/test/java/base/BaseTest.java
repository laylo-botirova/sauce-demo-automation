package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.ConfigReader;
import utils.DriverFactory;


public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {

        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.get("browser");
        }

        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod
    public void teardown() {
       if (driver != null) {
            driver.quit();
        }
    }
    }

