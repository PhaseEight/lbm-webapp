package com.logbookmanager.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@Profile(value = "integration-test")
@RunWith(SpringJUnit4ClassRunner.class)
/*
 * not necessary as this is declared in the
 * AbstractTransactionalJUnit4SpringContextTests
 */
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
/*
 * not necessary as this is declared in the
 * AbstractTransactionalJUnit4SpringContextTests
 */

@ContextConfiguration({"classpath:com/logbookmanager/configuration/spring/app-infrastructure.xml","classpath:com/logbookmanager/configuration/spring/app-property-configurator.xml"})
// not really necessary because these are the defaults
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResourcesUtilSpringContextTests extends AbstractJUnit4SpringContextTests {

	/** Inject Services */
	@Inject
	private ResourcesUtil resourcesUtil;

	@Before
	public void setup() {
		assertNotNull("resourcesUtil is null, testing will fail", resourcesUtil);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		logger.debug("We're tearing it down!");
	}

	@Test
	public void generalErrorMessage() {
		String message = "The process did not complete. Details should follow.";
		String key = "errors.general";
		assertTrue("key value does no equal: " + message, resourcesUtil.getProperty(key,new Object[]{}).equals(message));
		
	}
	
	public ResourcesUtil getResourcesUtil() {
		return resourcesUtil;
	}

	public void setResourcesUtil(ResourcesUtil resourcesUtil) {
		this.resourcesUtil = resourcesUtil;
	}

}
