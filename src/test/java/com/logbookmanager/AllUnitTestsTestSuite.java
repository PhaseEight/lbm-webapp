package com.logbookmanager;

import com.logbookmanager.domain.support.DomainSupportTestSuite;
import com.logbookmanager.spring.beans.SimpleDateFormatBeanDefinitionParserTests;
import com.logbookmanager.util.StringValidatorTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({SimpleDateFormatBeanDefinitionParserTests.class, StringValidatorTests.class, DomainSupportTestSuite.class})
public class AllUnitTestsTestSuite {

}
