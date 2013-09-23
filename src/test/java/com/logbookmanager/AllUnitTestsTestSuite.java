package com.logbookmanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.logbookmanager.domain.support.UserNameTests;
import com.logbookmanager.util.DateBeanTests;
import com.logbookmanager.util.StringValidatorTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DateBeanTests.class, StringValidatorTests.class, UserNameTests.class })
public class AllUnitTestsTestSuite {

}
