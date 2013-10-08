package com.logbookmanager.domain.support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.MessageFormat;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailAddressTests {

	private static final Logger logger = LoggerFactory.getLogger(EmailAddressTests.class.getName());

	private static Validator validator;

	@BeforeClass
	public static void setUpClass() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void emailAddressIsInvalid() {
		EmailAddress email = new EmailAddress("peter.neil@logmydiv.es.office");

		Set<ConstraintViolation<EmailAddress>> constraintViolations = validator.validate(email);

		assertEquals(1, constraintViolations.size());
		assertEquals("The email address does not match the prescribed pattern", constraintViolations.iterator().next()
				.getMessage());

	}

	@Test
	public void museumEmailAddressIsValid() {
		EmailAddress email = new EmailAddress("peter.neil@logmydiv.museum");

		Set<ConstraintViolation<EmailAddress>> constraintViolations = validator.validate(email);

		assertEquals(0, constraintViolations.size());

	}

	@Test
	public void allValidTLDs() {
		final String[] tlds = { "info", "mobi", "name", "aero", "jobs", "museum" };
		for (String tld : tlds) {
			EmailAddress email = new EmailAddress("peter.neil@logmydiv." + tld);

			Set<ConstraintViolation<EmailAddress>> constraintViolations = validator.validate(email);

			assertEquals(0, constraintViolations.size());

		}
	}

	@Test
	public void allValidLocalPart() {

		// ! # $ % & ' * + - / = ? ^ _ ` { | } ~
		String[] validLocalPartChars = { "!", "#", "$", "%", "&", "'", "*", "+", "-", "/", "=", "?", "^", "_", "`",
				"{", "|", "}", "~" };

		for (String validLocalPartChar : validLocalPartChars) {
			EmailAddress email = new EmailAddress("peter" + validLocalPartChar + "neil@logmydiv.es");

			Set<ConstraintViolation<EmailAddress>> constraintViolations = validator.validate(email);

			if (constraintViolations.size() > 0) {
				logger.debug(constraintViolations.iterator().next().getMessage() + "; " + email.getAddress());
			}

			assertEquals(0, constraintViolations.size());

		}

	}

	@Test
	public void invalidTLDs() {
		final String[] tlds = { "office", "world", "crazy", "saffa", "brummie", "foobar" };
		for (String tld : tlds) {
			EmailAddress email = new EmailAddress("peter.neil@logmydiv." + tld);

			Set<ConstraintViolation<EmailAddress>> constraintViolations = validator.validate(email);

			assertEquals(1, constraintViolations.size());

		}

	}

	@Test
	public void emailAddressIsValid() {
		String testAddress = "peter.neil@logmydiv.es";
		EmailAddress email = new EmailAddress(testAddress);

		Set<ConstraintViolation<EmailAddress>> constraintViolations = validator.validate(email);

		assertEquals(MessageFormat.format("There should be no errors with this email address {0};", testAddress), 0,
				constraintViolations.size());
	}

	@Test
	public void emailAddressNotEmptyContruction() {
		try {
			EmailAddress email = new EmailAddress("");
			fail("email addresses creation should have failed before reaching this Assertion" + email.getAddress());
		} catch (IllegalArgumentException iae) {
			logger.debug(iae.getMessage());
		}
	}

	@Test
	public void invalidSpaces() {
		String testAddress = "\"peter neil\"@logmydiv.es";
		EmailAddress email = new EmailAddress();
		Set<ConstraintViolation<EmailAddress>> constraintViolations = validator.validate(email);

		assertEquals(MessageFormat.format("this should fail for email address {0};", testAddress), 1,
				constraintViolations.size());

		testAddress = "peter neil@logmydiv.es";
		email = new EmailAddress();
		constraintViolations = validator.validate(email);

		assertEquals(MessageFormat.format("this should fail for email address {0};", testAddress), 1,
				constraintViolations.size());

	}

	@Test
	public void validTag() {
		String testAddress = "peter.neil+pedro.neil@logmydiv.es";
		EmailAddress email = new EmailAddress(testAddress);

		Set<ConstraintViolation<EmailAddress>> constraintViolations = validator.validate(email);

		assertEquals(MessageFormat.format("There should be no errors with this email address {0};", testAddress), 0,
				constraintViolations.size());
	}

	@Test
	public void periodAtStartFailure() {
		String testAddress = ".peter.neil+pedro.neil@logmydiv.es";
		EmailAddress email = new EmailAddress(testAddress);

		Set<ConstraintViolation<EmailAddress>> constraintViolations = validator.validate(email);

		assertEquals(MessageFormat.format("There should be no errors with this email address {0};", testAddress), 1,
				constraintViolations.size());

	}

	@Test
	public void periodAtEndFailure() {
		String testAddress = "peter.neil+pedro.neil.@logmydiv.es";
		EmailAddress email = new EmailAddress(testAddress);

		Set<ConstraintViolation<EmailAddress>> constraintViolations = validator.validate(email);

		assertEquals(MessageFormat.format("There should be no errors with this email address {0};", testAddress), 1,
				constraintViolations.size());
	}

}
