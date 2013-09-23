package com.logbookmanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.logbookmanager.faces.FacesIntegrationTestSuite;
import com.logbookmanager.mvc.MVCIntegrationTestSuite;
import com.logbookmanager.repository.RepositoryIntegrationTestSuite;
import com.logbookmanager.service.ServicesIntegrationTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ FacesIntegrationTestSuite.class, MVCIntegrationTestSuite.class, RepositoryIntegrationTestSuite.class,
		ServicesIntegrationTestSuite.class })
public class AllIntegrationTestsTestSuite {

}
