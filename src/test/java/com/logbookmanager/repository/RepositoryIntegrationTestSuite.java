package com.logbookmanager.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CountryRepositoryIntegrationTests.class, LogbookIntegrationTests.class,
		OrganisationRepositoryIntegrationTests.class })
public class RepositoryIntegrationTestSuite {

}
