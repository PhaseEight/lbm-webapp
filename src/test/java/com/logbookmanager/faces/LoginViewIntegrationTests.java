package com.logbookmanager.faces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

@ActiveProfiles({ "mvc-integration-test" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
		@ContextConfiguration("file:src/main/resources/com/logbookmanager/configuration/spring/app-root.xml"),
		@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/servlet-context.xml" }) })
@WebAppConfiguration(value = "src/main/webapp")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, ServletTestExecutionListener.class,DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class LoginViewIntegrationTests {
	
	@Autowired
	private WebApplicationContext wac;

	
	@Autowired
	private HttpSession session;

	
	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.addFilter(springSecurityFilterChain).build();

		Enumeration<String> list = wac.getServletContext().getInitParameterNames();

		while (list.hasMoreElements()) {
			final String name = list.nextElement();
			System.out.println("Parameter value: " + name + "Parameter Name: "
					+ wac.getServletContext().getInitParameter(name));
		}
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
	public void getLoginView() throws Exception {

		MvcResult result = this.mockMvc
				.perform(get("/app/welcome")
				.accept(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk())
				.andExpect(view().name("app/welcome"))
				.andDo(print())
				.andReturn();
		assertNotNull("Result must not be null", result);
	}
	
	@Test
	public void getUnauthenticatedMainViewReturnsLoginView() throws Exception {
		MvcResult result = this.mockMvc
				.perform(get("/app/main")
				.accept(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().is(302))
				.andDo(print())
				.andReturn();
		org.junit.Assert.assertEquals("http://localhost/app/welcome",result.getResponse().getRedirectedUrl());
		assertNotNull("Result must not be null", result);
	}

	
	//http://stackoverflow.com/questions/15203485/spring-test-security-how-to-mock-authentication
	@Test
	public void getAuthenticatedMainViewReturnsLoginView() throws Exception {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_LOGBOOKUSER"));
		Authentication authToken = new UsernamePasswordAuthenticationToken("peterneil","password",authorities);
		SecurityContextHolder.getContext().setAuthentication(authToken);
		
		MvcResult result = this.mockMvc
				.perform(get("/app/main")
				.accept(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().is(200))
				.andDo(print())
				.andReturn();
		assertEquals("http://localhost/app/main", result.getResponse().getRedirectedUrl());
	}
	
	public WebApplicationContext getWebApplicationContext() {
		return wac;
	}

	public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
		this.wac = webApplicationContext;
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
}
