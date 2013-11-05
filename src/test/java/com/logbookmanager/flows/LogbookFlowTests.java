package com.logbookmanager.flows;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;


public class LogbookFlowTests extends AbstractXmlFlowExecutionTests {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private HttpSession session;

	@Override
	protected FlowDefinitionResource[] getModelResources(FlowDefinitionResourceFactory resourceFactory) {
		return super.getModelResources(resourceFactory);
	}

	@Override
	protected void registerMockFlowBeans(ConfigurableBeanFactory flowBeanFactory) {
		super.registerMockFlowBeans(flowBeanFactory);
	}

	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return resourceFactory.createFileResource("src/main/webapp/WEB-INF/flows/admin/logbook/logbook-flow.xml");
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

}