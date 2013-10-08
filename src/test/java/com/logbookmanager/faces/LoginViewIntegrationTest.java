package com.logbookmanager.faces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.HashMap;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.apache.myfaces.test.base.junit4.AbstractJsfTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.faces.webflow.JSFMockHelper;
import org.springframework.faces.webflow.MockViewHandler;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.LocalParameterMap;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;
import org.springframework.webflow.test.MockExternalContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

@ActiveProfiles({ "mvc-integration-test" })
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@ContextConfiguration(name = "lbm-test-dispatcher-servlet", locations = {
		"file:src/main/webapp/WEB-INF/spring/servlet-context.xml",
		"file:src/main/resources/com/logbookmanager/configuration/spring/app-root.xml" })
@WebAppConfiguration(value = "src/main/webapp")
// not really necessary because these are the defaults
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginViewIntegrationTest {

	private static final Logger logger = LoggerFactory.getLogger(LoginViewIntegrationTest.class.getName());

	@Autowired
	WebApplicationContext wac; // cached
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	private MockMvc mockMvc;

	private final JSFMockHelper jsfMock = new JSFMockHelper();

	@Before
	public void setup() throws Exception {

		this.jsfMock.setUp();
		this.jsfMock.application().setViewHandler(new ResourceCheckingViewHandler());

		mockMvc = webAppContextSetup(wac).alwaysDo(print())
				.defaultRequest(get("/web/app").accept(MediaType.APPLICATION_FORM_URLENCODED))
				.addFilter(springSecurityFilterChain).build();

	}

	@After
	public void tearDown() throws Exception {
		logger.debug("We're tearing it down!");
		this.jsfMock.tearDown();
	}

	@Test
	public void getWelcomeUrl() throws Exception {

		this.mockMvc.perform(get("/web/app/welcome").contextPath("/web").servletPath("/app"))
				.andExpect(status().isOk());

	}

	private class ResourceCheckingViewHandler extends MockViewHandler {

		public UIViewRoot createView(FacesContext context, String viewId) {
			assertNotNull(viewId);
			assertEquals("welcome.xhtml", viewId);
			return new UIViewRoot();
		}

	}

}
