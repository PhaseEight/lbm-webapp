package com.logbookmanager.mvc;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.logbookmanager.domain.model.logbook.Logbook;
import com.logbookmanager.domain.support.Title;

@Profile(value = "mvc-integration-test")
@RunWith(SpringJUnit4ClassRunner.class)
/*
 * not necessary as this is declared in the
 * AbstractTransactionalJUnit4SpringContextTests
 */
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
/*
 * not necessary as this is declared in the
 * AbstractTransactionalJUnit4SpringContextTests
 */
@ContextHierarchy({ 
		@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/servlet-context.xml") })
@WebAppConfiguration(value = "src/main/webapp")
// not really necessary because these are the defaults
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogbookMVCIntegrationTests extends AbstractTransactionalJUnit4SpringContextTests {

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
		System.out.println("We're tearing it down!");
	}

	@Test
	public void addLogbook() throws Throwable {
		Logbook logbook = new Logbook(new Title("the_new_test_logbook", "The New Test Logbook"));
		mockMvc.perform(post("/app/logbook").sessionAttr("logbook", logbook));
	}

	@BeforeTransaction
	/*
	 * Start the test by making sure all the users in the database are set as
	 * Active and NOT deletes
	 */
	public void beforeTransaction() {
		String sql = "select id, version, active, LAST_UPDATE_DATE as luts, name as title_name, code as title_code from Logbook";
		List<Logbook> logbooks = jdbcTemplate.query(sql, new RowMapper<Logbook>() {
			public Logbook mapRow(ResultSet rs, int rowNum) throws SQLException {
				Title title = new Title(rs.getString("title_code"), rs.getString("title_name"));
				Logbook l = new Logbook(title);
				l.setId(rs.getLong("id"));
				l.setVersion(rs.getLong("version"));
				l.setlastUpdateTimeStamp(rs.getTimestamp("luts"));
				l.setActive(rs.getBoolean("active"));
				return l;
			}
		});
		for (Logbook logbook : logbooks) {
			System.out.println(logbook.toString());
		}
		assertNotNull("There must be some logbooks", logbooks);
	}

	@AfterTransaction
	public void afterTransaction() {
		System.out.println("Transaction Completed");
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
