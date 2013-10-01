package com.logbookmanager.domain.support;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.logbookmanager.domain.support.UserNameTests;
import com.logbookmanager.util.DateBeanTests;
import com.logbookmanager.util.StringValidatorTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ UserNameTests.class,EmailAddressTests.class })
public class DomainSupportTestSuite {

}
