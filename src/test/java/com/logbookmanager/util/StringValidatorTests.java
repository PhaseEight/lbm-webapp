package com.logbookmanager.util;

import org.junit.Assert;
import org.junit.Test;

public class StringValidatorTests {

	@Test
	public void stringIsAlphanumeric() {
		Assert.assertTrue("String was not UnderscoreAlphaNumericWithSpaces",StringValidator.isUnderscoreAlphaNumericWithSpaces("thisis 4lph4numeric_", 3));
	}

	@Test
	public void stringIsNotAlphanumeric() {
		Assert.assertFalse("String was not UnderscoreAlphaNumericWithoutSpaces",StringValidator.isUnderscoreAlphaNumericWithoutSpaces("@this_.is4lph4numeric", 3));
	}

	@Test
	public void stringIsAtLeastOneCharacterLongAndAlphanumeric() {
		Assert.assertTrue(StringValidator.isUnderscoreAlphaNumericWithoutSpaces("a", 1));
	}

	@Test
	public void stringIsAtLeastThreeCharactersLongAndAlphanumericWithUnderScore() {
		Assert.assertTrue(StringValidator.isUnderscoreAlphaNumericWithoutSpaces("_a", 3));
	}

	@Test
	public void stringIsAtLeastOneCharacterLongAndNotAlphaNumeric() {
		Assert.assertFalse(StringValidator.isUnderscoreAlphaNumericWithoutSpaces("@", 1));
	}

}
