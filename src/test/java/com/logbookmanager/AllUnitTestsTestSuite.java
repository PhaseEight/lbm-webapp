package com.logbookmanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.logbookmanager.domain.support.DomainSupportTestSuite;
import com.logbookmanager.spring.beans.SimpleDateFormatBeanDefinitionParserTests;
import com.logbookmanager.util.StringValidatorTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ SimpleDateFormatBeanDefinitionParserTests.class, StringValidatorTests.class, DomainSupportTestSuite.class })
public class AllUnitTestsTestSuite {

}
