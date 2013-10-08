package com.logbookmanager.faces;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.StringWriter;
import java.util.List;

import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

import org.apache.myfaces.test.base.junit4.AbstractJsfTestCase;
import org.apache.myfaces.test.mock.MockResponseWriter;
import org.apache.myfaces.test.mock.MockStateManager;
import org.apache.myfaces.test.mock.MockViewHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.faces.webflow.JsfView;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.webflow.execution.FlowExecutionContext;
import org.springframework.webflow.execution.FlowExecutionKey;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;
import org.springframework.webflow.test.MockExternalContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

/**
 * @see https
 *      ://cwiki.apache.org/confluence/display/MFTEST/Configure+MyFaces+Test
 *      +using+Maven
 * @see https://cwiki.apache.org/confluence/display/MFTEST/Index
 * @author Peter
 */
@ActiveProfiles({ "mvc-integration-test" })
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
@ContextConfiguration(name = "lbm-test-dispatcher-servlet", locations = {
		"file:src/main/webapp/WEB-INF/spring/servlet-context.xml",
		"file:src/main/resources/com/logbookmanager/configuration/spring/app-root.xml"

})
@WebAppConfiguration(value = "src/main/webapp")
// not really necessary because these are the defaults
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginAndSecurityIntegrationTests extends AbstractJsfTestCase {

	private static final Logger logger = LoggerFactory.getLogger(LoginAndSecurityIntegrationTests.class.getName());

	@Autowired
	WebApplicationContext wac; // cached
	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private MockHttpSession session = new MockHttpSession();

	private static final String VIEW_ID = "error/error.xhtml";
	private final MockExternalContext extContext = new MockExternalContext();

	private MockMvc mockMvc;

	private final JSFMockHelper jsfMock = new JSFMockHelper();

	private final StringWriter output = new StringWriter();

	private RequestContext requestContext = mock(RequestContext.class);
	private FlowExecutionContext flowExecutionContext = mock(FlowExecutionContext.class);

	private final FlowExecutionKey key = new FlowExecutionKey() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public String toString() {
			return "MOCK_KEY";
		}

		public boolean equals(Object o) {
			return true;
		}

		public int hashCode() {
			return 0;
		}
	};

	@Before
	public void setup() throws Exception {

		this.jsfMock.setUp();

		this.jsfMock.facesContext().getApplication().setViewHandler(new MockViewHandler());
		this.jsfMock.facesContext().getApplication().setStateManager(new TestStateManager());
		this.jsfMock.facesContext().setResponseWriter(new MockResponseWriter(this.output, null, null));

		UIViewRoot viewToRender = new UIViewRoot();
		viewToRender.setRenderKitId("HTML_BASIC");
		viewToRender.setViewId(VIEW_ID);
		this.jsfMock.facesContext().setViewRoot(viewToRender);

		UIForm form = new HtmlForm();
		form.setId("myForm");

		UIInput input = new HtmlInputText();
		input.setId("foo");

		form.getChildren().add(input);
		viewToRender.getChildren().add(form);

		RequestContextHolder.setRequestContext(this.requestContext);
		when(this.requestContext.getExternalContext()).thenReturn(this.extContext);
		when(this.requestContext.getFlowExecutionContext()).thenReturn(this.flowExecutionContext);
		when(this.flowExecutionContext.getKey()).thenReturn(this.key);
		new JsfView(viewToRender, this.jsfMock.lifecycle(), this.requestContext);

		wac.getServletContext();

		mockMvc = webAppContextSetup(wac).alwaysDo(print())
				.defaultRequest(get("/web/app").accept(MediaType.APPLICATION_FORM_URLENCODED))
				.addFilter(springSecurityFilterChain).build();

	}

	@After
	public void tearDown() throws Exception {
		logger.debug("We're tearing it down!");
		this.jsfMock.tearDown();
		RequestContextHolder.setRequestContext(null);
	}

	@Test
	public void doLogin() throws Exception {

		this.mockMvc
				.perform(
						post("/web/app/doLogin").param("loginForm:username", "peterneil")
								.param("loginForm:password", "password").contextPath("/web").servletPath("/app"))
				.andExpect(status().is(302)).andExpect(redirectedUrl("/web/app/main"));
	}

	@Test
	public void doLoginWithInvalidCredentials() throws Exception {

		this.mockMvc
				.perform(
						post("/web/app/doLogin").param("loginForm:username", "loginerrorusername")
								.param("loginForm:password", "password").contextPath("/web").servletPath("/app"))
				.andExpect(status().is(302)).andExpect(redirectedUrl("/web/app/welcome?login_error=1"));
	}

	@Test
	public void getUnauthenticatedMainViewReturnsWelcomeUrl() throws Exception {

		MvcResult result = this.mockMvc.perform(get("/web/app/main").contextPath("/web").servletPath("/app"))
				.andExpect(status().is(302)).andReturn();
		org.junit.Assert.assertEquals("http://localhost/web/app/welcome", result.getResponse().getRedirectedUrl());
		assertNotNull("Result must not be null", result);
	}

	@Test
	/**
	 * @see https://cwiki.apache.org/confluence/display/MFTEST/Configure+MyFaces+Test+using+Maven
	 * @see https://cwiki.apache.org/confluence/display/MFTEST/Index
	 * @throws Exception
	 */
	public void getAuthenticatedMainViewReturnsNoRedirect() throws Exception {

		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_LOGBOOKUSER");
		Authentication authToken = new UsernamePasswordAuthenticationToken("peterneil", "password", authorities);
		SecurityContext sc = new SecurityContextImpl();
		sc.setAuthentication(authToken);

		this.session
				.setAttribute(
						org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
						sc);

		try {
			this.mockMvc.perform(
					get("/web/app/main").session(session).principal(authToken).contextPath("/web").servletPath("/app"))
					.andExpect(status().is(200));
		} catch (Throwable t) {
			logger.debug(t.getLocalizedMessage());
		}

	}

	@Test
	/**
	 * Test src/main/resources/com/logbookmanager/configuration/spring/app-security.xml
	 * and make sure that the user is redirected to the welcome page if they do NOT have the correct ROLE_ADMIN authority  
	 * @throws Exception
	 */
	public void getAuthenticatedLogbookAdminViewWithInvalidRoles() throws Exception {

		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_LOGBOOKUSER");
		Authentication authToken = new UsernamePasswordAuthenticationToken("peterneil", "password", authorities);
		SecurityContext sc = new SecurityContextImpl();
		sc.setAuthentication(authToken);

		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

		this.mockMvc
				.perform(
						get("/web/app/admin/logbook").contextPath("/web").servletPath("/app").session(session)
								.principal(authToken)).andExpect(status().is(403)).andExpect(redirectedUrl(null));
	}

	private class TestStateManager extends MockStateManager {
		@SuppressWarnings("deprecation")
		public SerializedView saveSerializedView(FacesContext context) {
			SerializedView state = new SerializedView(new Object[] { "tree_state" }, new Object[] { "component_state" });
			return state;
		}
	}

}
