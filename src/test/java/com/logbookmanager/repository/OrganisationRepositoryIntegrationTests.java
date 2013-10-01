package com.logbookmanager.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.logbookmanager.data.repository.CountryRepository;
import com.logbookmanager.data.repository.OrganisationRepository;
import com.logbookmanager.domain.model.Country;
import com.logbookmanager.domain.model.organisation.Organisation;
import com.logbookmanager.domain.model.organisation.support.OrganisationDetails;
import com.logbookmanager.domain.model.support.Address;
import com.logbookmanager.domain.support.EmailAddress;
import com.logbookmanager.domain.support.Title;
import com.logbookmanager.domain.support.WebSite;
import com.logbookmanager.support.IntegrationTestSupport;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false )
public class OrganisationRepositoryIntegrationTests extends IntegrationTestSupport {

	/** Inject Services */
	@Inject
	private OrganisationRepository organisationRepository;
	
	@Inject
	private CountryRepository countryRepository;
	
	/** Inject session Factory for flushing */
	@Inject
	private SessionFactory sessionFactory;

	@Before
	public void setup() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		System.out.println("We're tearing it down!");
	}

	@Test
	@Transactional
	@Rollback(false)
	public void addOrganisation() throws Throwable {
		Title orgTitle = new Title("padi", "Professional Association of Diving Instructors");
		Country country = countryRepository.getCountry("ZA");
		Address orgAddress = new Address(country);
		EmailAddress orgEmail = new EmailAddress("info@padi.com");
		WebSite orgWebsite = new WebSite("http://www.padi.com");
	
		OrganisationDetails orgDetails = new OrganisationDetails(orgAddress,orgEmail,null,null,orgWebsite);
		Organisation org = new Organisation(orgTitle, orgDetails);
		addOrganisation(org);
		
	}

	@Test
	@Transactional
	@Rollback(false)
	public void addOrganisationUnregisteredCountry() throws Throwable {
		Title orgTitle = new Title("padi", "Professional Association of Diving Instructors");
		Country country = countryRepository.getCountry("DE");
		Address orgAddress = new Address(country);
		EmailAddress orgEmail = new EmailAddress("info@padi.com");
		WebSite orgWebsite = new WebSite("http://www.padi.com");
	
		OrganisationDetails orgDetails = new OrganisationDetails(orgAddress,orgEmail,null,null,orgWebsite);
		Organisation org = new Organisation(orgTitle, orgDetails);
		Organisation newOrg = addOrganisation(org);
		
		assertTrue("Organisation must be active",newOrg.isActive());
		
	}
	
	@Transactional
	@Rollback(false)
	private Organisation addOrganisation(Organisation organisation) throws Throwable {
		Organisation newOrg = organisationRepository.makePersistent(organisation);
		sessionFactory.getCurrentSession().flush();
		return newOrg;
	}

	@Test
	@Transactional
	public void listAll() throws Throwable {
		List<Organisation> list = organisationRepository.findAll();
		assertNotNull("A list should ber returned when looking for logbooks", list);
		assertTrue("there must be more than 1 logbook after creating a logbook", list.size() >= 1);
	}

	@BeforeTransaction
	public void beforeTransaction() {
		
	}	
	@AfterTransaction
	public void afterTransaction() {
		System.out.println("Transaction Completed");
	}

}
