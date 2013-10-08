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
	}

	@Test
	public void testWebFlowStartup() throws Exception {
		MutableAttributeMap<?> input = new LocalAttributeMap<Object>();
		MockExternalContext context = new MockExternalContext();
		context.setCurrentUser("peterneiladmin");
		startFlow(input, context);
		assertCurrentStateEquals("start");
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

}