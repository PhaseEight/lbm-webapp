package com.logbookmanager.mvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles({"mvc-integration-test", "default"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy(
		{ 
			@ContextConfiguration("file:src/main/resources/com/logbookmanager/configuration/spring/app-root.xml"),
			@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/servlet-context.xml"}) 
		}
	)
@WebAppConfiguration(value = "src/main/webapp")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignupControllerIntegrationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).alwaysExpect(status().isOk()).addFilter(springSecurityFilterChain).build();
//		this.mockMvc = MockMvcBuilders.standaloneSetup(new SignupController()).build();
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
	public void getSignupForm() throws Exception {
		this.mockMvc.perform(get("/user/signup")
				.accept(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isOk())
		.andExpect(view().name("signup-form")).andReturn();
	}

	@Test
	public void doSignup() throws Exception {
		System.out.println("createUploadFile called");
		this.mockMvc.perform(post("/user/signup").accept(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isOk())
		.andExpect(view().name("signup-success"));
		
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
