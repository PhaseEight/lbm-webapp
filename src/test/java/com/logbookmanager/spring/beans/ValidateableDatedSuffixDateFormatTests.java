package com.logbookmanager.spring.beans;

import com.logbookmanager.date.ValidateableSimpleDateFormat;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

public class ValidateableDatedSuffixDateFormatTests {

    private static ClassPathXmlApplicationContext ctx;

    @BeforeClass
    public static void beforeClass() {
        ctx = new ClassPathXmlApplicationContext(new String[]{"com/logbookmanager/ulm-util.xml"});
    }

    @Test
    public void validateableDatedSuffixDateFormat() {
        ValidateableSimpleDateFormat validateableDatedSuffixDateFormat = (ValidateableSimpleDateFormat) ctx
                .getBean("validateableDatedSuffixDateFormat");
        assertTrue(validateableDatedSuffixDateFormat.isValid());
    }

}
