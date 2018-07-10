package com.logbookmanager.web.pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Peter
 * @see <a
 * href="http://www.hostettler.net/blog/2012/04/05/programmatically-build-web-archives-using-shrinkwrap/">programmatically-build-web-archives-using-shrinkwrap</a>
 * @see <a
 * href="http://www.hostettler.net/blog/2012/04/09/embedded-jee-web-application-integration-testing-using-tomcat-7/">embedded-jee-web-application-integration-testing-using-tomcat-7</a>
 */
public class WelcomePageTest {

    final Logger log = LoggerFactory.getLogger(WelcomePageTest.class.getName());

    private int HTTP_PORT = 8080;

    private WebDriver driver;

    @Before
    public void setup() throws Throwable {
        log.debug("Create HtmlUnitDriver for WebDriver");
        driver = new HtmlUnitDriver(true);
    }

    @Test
    public void loginAsNonAdminFromWelcomePage() throws Exception {

        // And now use this to visit App Context
        // driver.get("http://localhost:HTTP_PORT/web/admin/welcome");
        // Alternatively the same thing can be done like this
        log.debug("navigating to /web");
        driver.navigate().to("http://localhost:" + HTTP_PORT + "/web");

        log.debug("make sure that there was redirect to /web/app/welcome");
        assertTrue("request to /web must redirect to /web/app/welcome; currentUrl is: " + driver.getCurrentUrl(),
                driver.getCurrentUrl().equals("http://localhost:" + HTTP_PORT + "/web/app/welcome"));

        log.debug("Finding the login controls");
        WebElement topLoginButton = driver.findElement(By.name("topLoginLogoutForm:topLoginButton"));
        WebElement loginFormUsername = driver.findElement(By.name("loginForm:username"));
        WebElement loginFormPassword = driver.findElement(By.name("loginForm:password"));
        WebElement loginFormLoginButton = driver.findElement(By.name("loginForm:loginButton"));

        log.debug("Showing login form");
        topLoginButton.click();

        log.debug("entering username");
        loginFormUsername.sendKeys("peterneil");
        log.debug("entering password");
        loginFormPassword.sendKeys("password");

        log.debug("submit login form");
        loginFormLoginButton.submit();

        log.debug("wait for the main page to load");
        assertTrue("Main page must be loaded", (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                log.debug("Page title is: " + d.getTitle());
                log.debug("Page current URL: " + d.getCurrentUrl());
                return ((d.getTitle().toLowerCase().startsWith("dashboard")) && d.getCurrentUrl().equalsIgnoreCase(
                        "http://localhost:" + HTTP_PORT + "/web/app/main"));
            }
        }));

        // check that the logout button is visible
        log.debug("checking logout button is available");
        WebElement topLogoutButton = driver.findElement(By.name("topLoginLogoutForm:topLogoutButton"));
        assertTrue(topLogoutButton.isDisplayed());

        log.debug("make sure that the top login button has not rendered");
        try {
            topLoginButton = driver.findElement(By.name("topLoginLogoutForm:topLoginButton"));
            fail("login button was found");
        } catch (NoSuchElementException nse) {
            log.debug("login button is not in the page");
        }
    }

    @After
    public final void tearDown() throws Throwable {
        driver.quit();
    }

}
