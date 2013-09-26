package com.logbookmanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.logbookmanager.web.pages.WebPagesTestSuite;
import com.logbookmanager.web.pages.WelcomePageTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AllUnitTestsTestSuite.class, AllIntegrationTestsTestSuite.class, WebPagesTestSuite.class })
public class AllTestsTestSuite {

}
