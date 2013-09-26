package com.logbookmanager.web.pages;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class WelcomePageTest {

	final static Logger log = LoggerFactory.getLogger(WelcomePageTest.class
			.getName());

	@Test
	public void loginAsNonAdminFromWelcomePage() throws Exception {

		log.debug("Create HtmlUnitDriver for WebDriver");
		WebDriver driver = new HtmlUnitDriver(true);

		try {
			// And now use this to visit App Context
			// driver.get("http://localhost:8080/web/admin/welcome");
			// Alternatively the same thing can be done like this
			log.debug("navigating to /web");
			driver.navigate().to("http://localhost:8080/web");

			log.debug("make sure that there was redirect to /web/app/welcome");
			assertTrue(
					"request to /web must redirect to /web/app/welcome; currentUrl is: "
							+ driver.getCurrentUrl(), driver.getCurrentUrl()
							.equals("http://localhost:8080/web/app/welcome"));

			log.debug("Finding the login controls");
			WebElement topLoginButton = driver.findElement(By
					.name("topLoginLogoutForm:topLoginButton"));
			WebElement loginFormUsername = driver.findElement(By
					.name("loginForm:j_username"));
			WebElement loginFormPassword = driver.findElement(By
					.name("loginForm:j_password"));
			WebElement loginFormLoginButton = driver.findElement(By
					.name("loginForm:loginButton"));

			log.debug("Showing login form");
			topLoginButton.click();

			log.debug("entering username");
			loginFormUsername.sendKeys("peterneil");
			log.debug("entering password");
			loginFormPassword.sendKeys("password");

			log.debug("submit login form");
			loginFormLoginButton.submit();

			log.debug("wait for the main page to load");
			assertTrue("Main page must be loaded", (new WebDriverWait(driver,
					10)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					log.debug("Page title is: " + d.getTitle());
					log.debug("Page current URL: " + d.getCurrentUrl());
					return ((d.getTitle().toLowerCase().startsWith("dashboard")) && d
							.getCurrentUrl().equalsIgnoreCase(
									"http://localhost:8080/web/app/main"));
				}
			}));

			// check that the logout button is visible
			log.debug("checking logout button is available");
			WebElement topLogoutButton = driver.findElement(By
					.name("topLoginLogoutForm:topLogoutButton"));
			assertTrue(topLogoutButton.isDisplayed());

			log.debug("make sure that the top login button has not rendered");
			try {
				topLoginButton = driver.findElement(By
						.name("topLoginLogoutForm:topLoginButton"));
				fail("login button was found");
			} catch (NoSuchElementException nse) {
				log.debug("login button is not in the page");
			}
			
		} finally {
			driver.quit();
		}

	}

}
