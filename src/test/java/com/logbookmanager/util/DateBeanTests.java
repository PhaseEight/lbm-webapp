package com.logbookmanager.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.logbookmanager.date.ValidateableSimpleDateFormat;

public class DateBeanTests {

	private static ClassPathXmlApplicationContext ctx;

	
	@BeforeClass
	public static void beforeClass() {
		ctx = new ClassPathXmlApplicationContext(
				new String[] { "com/logbookmanager/ulm-util.xml" });
	}

	@Before
	public void beforeMethodExecution() {

	}

	@Test
	public void datedSuffixDateFormat() {
		String datedSuffixDateFormatRegEx = (String) ctx
				.getBean("datedSuffixDateFormatRegEx");
		SimpleDateFormat datedSuffixDateFormat = (SimpleDateFormat) ctx
				.getBean("datedSuffixDateFormat");
		Pattern validDatePattern = Pattern.compile(datedSuffixDateFormatRegEx);
		Matcher matcher = validDatePattern.matcher(datedSuffixDateFormat
				.format(new Date()));

		
		Logger log = org.slf4j.LoggerFactory.getLogger(DateBeanTests.class);
		log.debug("Here's a slf4j then!");
		assertTrue(matcher.matches());

	}

	@Test
	public void timeOfDayOfYearDateFormat() {
		String timeOfDayOfYearDateFormatRegEx = (String) ctx
				.getBean("timeOfDayOfYearDateFormatRegEx");
		SimpleDateFormat timeOfDayOfYearDateFormat = (SimpleDateFormat) ctx
				.getBean("timeOfDayOfYearDateFormat");

		assertNotNull("timeOfDayOfYearDateFormat is null",
				timeOfDayOfYearDateFormat);
		String theDate = timeOfDayOfYearDateFormat.format(new Date());
		Pattern validDatePattern = Pattern
				.compile(timeOfDayOfYearDateFormatRegEx);
		Matcher matcher = validDatePattern.matcher(theDate);
		assertTrue("could not find any matches for RegEx: "
				+ timeOfDayOfYearDateFormatRegEx + " and date " + theDate,
				matcher.matches());
	}

	@Test
	public void validateableDatedSuffixDateFormat() {
		ValidateableSimpleDateFormat validateableDatedSuffixDateFormat = (ValidateableSimpleDateFormat) ctx
				.getBean("validateableDatedSuffixDateFormat");
		assertTrue(validateableDatedSuffixDateFormat.isValid());
	}

}
