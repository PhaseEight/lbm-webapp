package com.logbookmanager.spring.beans;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SimpleDateFormatBeanDefinitionParserTests {

    private static ClassPathXmlApplicationContext ctx;

    @BeforeClass
    public static void beforeClass() {
        ctx = new ClassPathXmlApplicationContext(new String[]{"com/logbookmanager/ulm-util.xml"});
    }

    @Before
    public void beforeMethodExecution() {

    }

    @Test
    public void datedSuffixDateFormat() {
        String datedSuffixDateFormatRegEx = (String) ctx.getBean("datedSuffixDateFormatRegEx");
        SimpleDateFormat datedSuffixDateFormat = (SimpleDateFormat) ctx.getBean("datedSuffixDateFormat");
        Pattern validDatePattern = Pattern.compile(datedSuffixDateFormatRegEx);
        Matcher matcher = validDatePattern.matcher(datedSuffixDateFormat.format(new Date()));

        Logger logger = LoggerFactory.getLogger(SimpleDateFormatBeanDefinitionParserTests.class.getName());
        logger.debug("Here's slf4j then!");
        assertTrue(matcher.matches());
    }

    @Test
    public void timeOfDayOfYearDateFormat() {
        String timeOfDayOfYearDateFormatRegEx = (String) ctx.getBean("timeOfDayOfYearDateFormatRegEx");
        SimpleDateFormat timeOfDayOfYearDateFormat = (SimpleDateFormat) ctx.getBean("timeOfDayOfYearDateFormat");

        assertNotNull("timeOfDayOfYearDateFormat is null", timeOfDayOfYearDateFormat);
        String theDate = timeOfDayOfYearDateFormat.format(new Date());
        Pattern validDatePattern = Pattern.compile(timeOfDayOfYearDateFormatRegEx);
        Matcher matcher = validDatePattern.matcher(theDate);
        assertTrue("could not find any matches for RegEx: " + timeOfDayOfYearDateFormatRegEx + " and date " + theDate,
                matcher.matches());
    }

}
