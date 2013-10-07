package com.logbookmanager.web.pages;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.catalina.LifecycleState;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.io.FileUtils;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.impl.base.exporter.zip.ZipExporterImpl;
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

/**
 * @see <a
 *      href="http://www.hostettler.net/blog/2012/04/05/programmatically-build-web-archives-using-shrinkwrap/">programmatically-build-web-archives-using-shrinkwrap</a>
 * @see <a
 *      href="http://www.hostettler.net/blog/2012/04/09/embedded-jee-web-application-integration-testing-using-Tomcat-7/">embedded-jee-web-application-integration-testing-using-Tomcat-7</a>
 * @author Peter
 * 
 */
public class TomcatEmbeddedTest {

	final static Logger log = LoggerFactory.getLogger(TomcatEmbeddedTest.class.getName());

	private static final String WEBAPP_SRC = "src/main/webapp";
	private static final String APP_RESOURCES = "src/main/resources";

	/** The Tomcat instance. */
	private Tomcat mTomcat;
	/** The temporary directory in which Tomcat and the app are deployed. */
	private String mWorkingDir = System.getProperty("java.io.tmpdir");
	
	private int HTTP_PORT = 8787;

	private String applicationId = "lbm-web";

	@Before
	public void setup() throws Throwable {

		mTomcat = new Tomcat();
		mTomcat.setPort(HTTP_PORT);
		mTomcat.setBaseDir(mWorkingDir);
		mTomcat.getHost().setAppBase(mWorkingDir);
		mTomcat.getHost().setAutoDeploy(true);
		mTomcat.getHost().setDeployOnStartup(true);

		String contextPath = "/" + getApplicationId();
		File webApp = new File(mWorkingDir, getApplicationId());
		File oldWebApp = new File(webApp.getAbsolutePath());
		FileUtils.deleteDirectory(oldWebApp);
		File exportDir = new File(mWorkingDir + "/" + getApplicationId() + ".war");
		log.debug("Working dir: " + exportDir.getAbsoluteFile());
		new ZipExporterImpl(createWebArchive()).exportTo(exportDir, true);
		mTomcat.addWebapp(mTomcat.getHost(), contextPath, webApp.getAbsolutePath());

		mTomcat.start();

	}

	@Test
	public void loginAsNonAdminFromWelcomePage() throws Exception {

		log.debug("Create HtmlUnitDriver for WebDriver");
		WebDriver driver = new HtmlUnitDriver(true);

		try {
			// And now use this to visit App Context
			// driver.get("http://localhost:HTTP_PORT/web/admin/welcome");
			// Alternatively the same thing can be done like this
			log.debug("navigating to /web");
			driver.navigate().to("http://localhost:" + HTTP_PORT + "/web");

			log.debug("make sure that there was redirect to /web/app/welcome");
			assertTrue("request to /web must redirect to /web/app/welcome; currentUrl is: " + driver.getCurrentUrl(), driver
					.getCurrentUrl().equals("http://localhost:" + HTTP_PORT + "/web/app/welcome"));

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

		} finally {
			driver.quit();
		}

	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@After
	public final void tearDown() throws Throwable {
		if (mTomcat.getServer() != null && mTomcat.getServer().getState() != LifecycleState.DESTROYED) {
			if (mTomcat.getServer().getState() != LifecycleState.STOPPED) {
				mTomcat.stop();
			}
			mTomcat.destroy();
		}
	}

	private WebArchive createWebArchive() {
		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war");

		war.merge(
				ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class).importDirectory(WEBAPP_SRC)
						.as(GenericArchive.class), "/", Filters.includeAll());
		
		war.merge(
				ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class).importDirectory(APP_RESOURCES)
						.as(GenericArchive.class), "/WEB-INF/classes", Filters.includeAll());
		
		
		war.setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
				.addPackage("com.logbookmanager")
				.addClass(org.springframework.util.CachingMapDecorator.class).addClass(org.jodah.typetools.TypeResolver.class)
				.addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/faces-config.xml"))
				.addAsWebResource(new File(WEBAPP_SRC, "index.htm"));

		return war;
	}

}
