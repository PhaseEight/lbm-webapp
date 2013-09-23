package com.logbookmanager.flows;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

import com.logbookmanager.web.component.DashboardBean;

/*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
		@ContextConfiguration("file:src/main/resources/com/logbookmanager/configuration/spring/app-root.xml"),
		@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/servlet-context.xml" }) })
@WebAppConfiguration(value = "src/main/webapp")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, ServletTestExecutionListener.class,DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
*/
public class MainFlowTests extends AbstractXmlFlowExecutionTests {

	private DashboardBean dashboardBean = new DashboardBean();
	
	@Autowired
	private WebApplicationContext wac;

	
	@Autowired
	private HttpSession session;

	
	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private MockMvc mockMvc;

	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return resourceFactory.createFileResource("src/main/webapp/WEB-INF/flows/main/main-flow.xml");
	}

	@Override
	protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
		builderContext.registerBean("dashboardBean", dashboardBean);
	}

	@Test
	public void testWebFlowStartup() throws Exception {
		MutableAttributeMap<?> input = new LocalAttributeMap<Object>();
		MockExternalContext context = new MockExternalContext();
		context.setCurrentUser("peterneiladmin");
		startFlow(input,context);
		assertCurrentStateEquals("start");
		assertTrue(getRequiredFlowAttribute("dashboardBean") instanceof DashboardBean);
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

}