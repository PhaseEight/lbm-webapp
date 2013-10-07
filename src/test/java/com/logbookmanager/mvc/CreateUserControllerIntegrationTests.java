package com.logbookmanager.mvc;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

@Profile(value = "integration-test, mvc-integration-test")
@RunWith(SpringJUnit4ClassRunner.class)
/*
 * AbstractTransactionalJUnit4SpringContextTests
 */
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
/*
 * not necessary as this is declared in the
 * AbstractTransactionalJUnit4SpringContextTests
 */
@ContextHierarchy({ 
		@ContextConfiguration("classpath:com/logbookmanager/configuration/spring/servlet-context.xml"),
		@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/servlet-context.xml") })
@WebAppConfiguration(value = "src/main/webapp")
// not really necessary because these are the defaults
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateUserControllerIntegrationTests extends ModelAndViewAssert {

	private static final Logger logger = LoggerFactory.getLogger(CreateUserControllerIntegrationTests.class.getName());
	
	@Inject
	private WebApplicationContext webApplicationContext;

	@Resource
	private FilterChainProxy springSecurityFilterChain;

	/** Inject session Factory for flushing */
	@Inject
	private SessionFactory sessionFactory;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		setMockMvc(MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain)
				.build());
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		logger.debug("We're tearing it down!");
	}

	
	@Test
	public void createUserViewOnGet() {
		
	}
	
	public WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}

	public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
		this.webApplicationContext = webApplicationContext;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
}
