package com.logbookmanager.domain.support;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNameTests {

	private static final Logger logger = LoggerFactory.getLogger(UserNameTests.class.getName());

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUserNameString() {
	}

	@Test
	public void valueObjectSupportObjectsAreEqual() {
		String usernameString1 = new String("PeterNeil");
		String usernameString2 = new String("PeterNeil");

		assertTrue(usernameString1 != usernameString2);
		assertTrue(usernameString1.equals(usernameString2));

		UserName username1 = new UserName(usernameString1);
		UserName username2 = new UserName(usernameString2);

		assertTrue("The two valueobjects must be equal if the value property is the same string",
				username1.equals(username2));
		assertTrue("The two valueobjects must be see the USERNAME value of to be the same",
				username1.sameValueAs(username2));

		logger.debug(username1.toString());
		logger.debug(username2.toString());

	}

	@Test
	public void valueObjectSupportObjectsAreNotEqual() {
		String usernameString1 = new String("PeterNeil");
		String usernameString2 = new String("PeterNeil1");

		assertTrue(usernameString1 != usernameString2);
		assertFalse("string 1 and string 2 must NOT be equal", usernameString1.equals(usernameString2));

		UserName username1 = new UserName(usernameString1);
		UserName username2 = new UserName(usernameString2);

		assertFalse("The two valueobjects must NOT be equal if the value property is the same string",
				username1.equals(username2));
		assertFalse("The two valueobjects must NOT be see the USERNAME value of to be the same",
				username1.sameValueAs(username2));

		logger.debug(username1.toString());
		logger.debug(username2.toString());
	}
}
