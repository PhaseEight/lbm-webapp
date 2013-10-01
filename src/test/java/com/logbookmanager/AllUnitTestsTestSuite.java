package com.logbookmanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.logbookmanager.domain.support.DomainSupportTestSuite;
import com.logbookmanager.domain.support.EmailAddressTests;
import com.logbookmanager.domain.support.UserNameTests;
import com.logbookmanager.util.DateBeanTests;
import com.logbookmanager.util.StringValidatorTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DateBeanTests.class, StringValidatorTests.class, DomainSupportTestSuite.class})
public class AllUnitTestsTestSuite {

}
