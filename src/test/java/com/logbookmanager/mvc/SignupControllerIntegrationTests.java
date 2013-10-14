package com.logbookmanager.mvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.logbookmanager.support.IntegrationTestSupport;
import com.logbookmanager.web.controller.SignupController;

@ActiveProfiles({ "mvc-integration-test", "default" })
public class SignupControllerIntegrationTests extends IntegrationTestSupport {

	private static final Logger logger = LoggerFactory.getLogger(SignupControllerIntegrationTests.class.getName());

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// this.mockMvc =
		// MockMvcBuilders.webAppContextSetup(this.webApplicationContext).alwaysExpect(status().isOk()).addFilter(springSecurityFilterChain).build();
		this.mockMvc = MockMvcBuilders.standaloneSetup(new SignupController()).build();
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
	public void getSignupForm() throws Exception {
		this.mockMvc.perform(get("/user/signup").accept(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk()).andExpect(view().name("signup-form"))
				.andExpect(view().name("signup-form")).andReturn();
	}

	@Test
	public void doSignup() throws Exception {
		logger.debug("createUploadFile called");
		this.mockMvc.perform(post("/user/signup").accept(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk()).andExpect(view().name("signup-success"));
	}

	public WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}

	public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
		this.webApplicationContext = webApplicationContext;
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
}
