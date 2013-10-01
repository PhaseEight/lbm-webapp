package com.logbookmanager.util;

public class StringValidator {

	final public static String EMAIL_PATTERN = "^[!#$%&'*+-/=?^_`{|}~A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.([A-Za-z]{2,4}|museum))$";

	public static final StringValidator instance = new StringValidator();

	public static boolean isUnderscoreAlphaNumericWithSpaces(String s, int minimumLength) {
		/**
		 * only test the string if it's at least the minimum Length
		 */
		String vs = s.replace(" ", "");
		return isUnderscoreAlphaNumericWithoutSpaces(vs, minimumLength);

	}

	public static boolean isUnderscoreAlphaNumericWithoutSpaces(String value, int minimumLength) {
		/**
		 * only test the string if it's at least the minimum Length
		 */
		if (value.length() >= (minimumLength - 1)) {
			String pattern = "^[a-zA-Z0-9_]*$";
			String pattern2 = "^\\w*$";
			return matches(value, new String[] { pattern, pattern2 });
		} else {
			return false;
		}
	}

	public static boolean matches(String value, String... patterns) {
		// test each pattern and return fals as soo as one does not match
		for (String pattern : patterns) {
			if (!value.matches(pattern)) {
				return false;
			}
		}
		return true;
	}

	private StringValidator() {

	}
}
