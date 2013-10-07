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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.logbookmanager.domain.model.logbook.Logbook;
import com.logbookmanager.domain.support.Title;
import com.logbookmanager.support.IntegrationTestSupport;

@ActiveProfiles (value = "default, mvc-integration-test")
@RunWith(SpringJUnit4ClassRunner.class)
/*
 * not necessary as this is declared in the
 */
public class LogbookMVCIntegrationTests extends IntegrationTestSupport {

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
			logger.debug(logbook.toString());
		}
		assertNotNull("There must be some logbooks", logbooks);
	}

	@AfterTransaction
	public void afterTransaction() {
		logger.debug("Transaction Completed");
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
