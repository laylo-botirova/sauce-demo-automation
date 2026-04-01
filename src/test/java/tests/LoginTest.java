package tests;

import base.BaseTest;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.LoggerUtil;

public class LoginTest extends BaseTest {

    Logger log = LoggerUtil.getLogger(LoginTest.class);

    @Test
    public void validLoginTest() {

        log.info("Starting login test");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        String currentUrl = driver.getCurrentUrl();

        log.info("Current URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("inventory"));

        log.info("Login test passed");
    }

    @Test
    public void invalidLoginTest() {

        log.info("Starting invalid login test");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("invalid_user", "wrong_pass");

        String currentUrl = driver.getCurrentUrl();

        log.info("Current URL: " + currentUrl);

        Assert.assertFalse(currentUrl.contains("inventory"));

        log.info("Invalid login test passed");
    }
}