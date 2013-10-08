package com.logbookmanager.faces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.event.PostRestoreStateEvent;
import javax.faces.event.SystemEvent;
import javax.faces.lifecycle.Lifecycle;

import org.apache.el.ExpressionFactoryImpl;
import org.apache.myfaces.test.base.junit4.AbstractJsfTestCase;
import org.apache.myfaces.test.mock.MockApplication20;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.expression.ExpressionParser;
import org.springframework.binding.expression.support.FluentParserContext;
import org.springframework.faces.webflow.FlowFacesContext;
import org.springframework.faces.webflow.JSFMockHelper;
import org.springframework.faces.webflow.JsfView;
import org.springframework.faces.webflow.JsfViewFactory;
import org.springframework.faces.webflow.MockBaseFacesContext;
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
import org.springframework.webflow.execution.View;
import org.springframework.webflow.execution.ViewFactory;
import org.springframework.webflow.expression.el.WebFlowELExpressionParser;
import org.springframework.webflow.test.MockExternalContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

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
public class LoginViewIntegrationTest extends AbstractJsfTestCase {

	private static final Logger logger = LoggerFactory.getLogger(LoginViewIntegrationTest.class.getName());

	@Autowired
	WebApplicationContext wac; // cached
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	private MockMvc mockMvc;


	
	
	private static final String VIEW_ID = "/testView.xhtml";

	private ViewFactory factory;

	private final JSFMockHelper jsfMock = new JSFMockHelper();

	private final RequestContext context = mock(RequestContext.class);

	private final LocalAttributeMap<Object> flashMap = new LocalAttributeMap<Object>();

	private final ViewHandler viewHandler = new MockViewHandler();

	private Lifecycle lifecycle;

	private PhaseListener trackingListener;

	private final ExpressionParser parser = new WebFlowELExpressionParser(new ExpressionFactoryImpl());

	private final MockExternalContext extContext = new MockExternalContext();

	private final MockServletContext servletContext = new MockServletContext();

	private final MockHttpServletRequest request = new MockHttpServletRequest();

	private final MockHttpServletResponse response = new MockHttpServletResponse();

	private LocalAttributeMap<Object> viewScope = new LocalAttributeMap<Object>();

	@Before
	public void setup() throws Exception {

		configureJsf();

		
		this.extContext.setNativeContext(this.servletContext);
		this.extContext.setNativeRequest(this.request);
		this.extContext.setNativeResponse(this.response);
		RequestContextHolder.setRequestContext(this.context);
		when(this.context.getViewScope()).thenReturn(this.viewScope);
		when(this.context.getFlashScope()).thenReturn(this.flashMap);
		when(this.context.getExternalContext()).thenReturn(this.extContext);
		when(this.context.getRequestParameters()).thenReturn(
				new LocalParameterMap(new HashMap<String, Object>()));
		
		
		
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
	public void getLoginView() throws Exception {

		this.mockMvc.perform(get("/web/app/welcome").contextPath("/web").servletPath("/app"))
				.andExpect(status().isOk()).andExpect(view().name("app/welcome"));
	}

	@Test
	public void doLogin() throws Exception {

		this.mockMvc
				.perform(
						post("/web/app/doLogin").param("loginForm:username", "peterneil")
								.param("loginForm:password", "password").contextPath("/web").servletPath("/app"))
				.andExpect(status().is(302)).andExpect(redirectedUrl("/web/app/main"));
	}

	
	/**
	 * View has not yet been created
	 */
	@Test
	public final void createNewView() {

		this.factory = new JsfViewFactory(this.parser.parseExpression(VIEW_ID,
				new FluentParserContext().template().evaluate(RequestContext.class).expectResult(String.class)),
				this.lifecycle);

		MockUIViewRoot newRoot = new MockUIViewRoot();
		newRoot.setViewId(VIEW_ID);
		((MockViewHandler) this.viewHandler).setCreateView(newRoot);
		this.context.inViewState();

		View newView = this.factory.getView(this.context);

		assertNotNull("A View was not created", newView);
		assertTrue("A JsfView was expected", newView instanceof JsfView);
		assertEquals("View name did not match", VIEW_ID, ((JsfView) newView).getViewRoot().getViewId());
		assertFalse("An unexpected event was signaled,", newView.hasFlowEvent());
	}
	
	private void configureJsf() throws Exception {
		this.jsfMock.setUp();
		ExceptionEventAwareMockApplication application = new ExceptionEventAwareMockApplication();
		((MockBaseFacesContext) FlowFacesContext.getCurrentInstance()).setApplication(application);
		this.trackingListener = new TrackingPhaseListener();
		this.jsfMock.lifecycle().addPhaseListener(this.trackingListener);
		this.jsfMock.facesContext().setViewRoot(null);
		this.jsfMock.facesContext().getApplication().setViewHandler(this.viewHandler);
	}
	
	private class TrackingPhaseListener implements PhaseListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final List<String> phaseCallbacks = new ArrayList<String>();

		public void afterPhase(PhaseEvent event) {
			String phaseCallback = "AFTER_" + event.getPhaseId();
			assertFalse("Phase callback " + phaseCallback + " already executed.",
					this.phaseCallbacks.contains(phaseCallback));
			this.phaseCallbacks.add(phaseCallback);
		}

		public void beforePhase(PhaseEvent event) {
			String phaseCallback = "BEFORE_" + event.getPhaseId();
			assertFalse("Phase callback " + phaseCallback + " already executed.",
					this.phaseCallbacks.contains(phaseCallback));
			this.phaseCallbacks.add(phaseCallback);
		}

		public PhaseId getPhaseId() {
			return PhaseId.ANY_PHASE;
		}
	}

	
	protected class TestBean {

		UIOutput output;
		UIInput input;

		public UIOutput getOutput() {
			return this.output;
		}

		public void setOutput(UIOutput output) {
			this.output = output;
		}

		public UIInput getInput() {
			return this.input;
		}

		public void setInput(UIInput input) {
			this.input = input;
		}
	}
	
	private static class MockUIViewRoot extends UIViewRoot {

		private boolean throwOnPostRestoreStateEvent;
		private AbortProcessingException abortProcessingException;

		public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
			if (event instanceof PostRestoreStateEvent) {
				assertSame("Component did not match", this, ((PostRestoreStateEvent) event).getComponent());
				if (this.throwOnPostRestoreStateEvent) {
					this.abortProcessingException = new AbortProcessingException();
					throw this.abortProcessingException;
				}
			}
		}
	}
	
	private static class ExceptionEventAwareMockApplication extends MockApplication20 {

		public void publishEvent(FacesContext facesContext, Class<? extends SystemEvent> systemEventClass, Object source) {
			if (ExceptionQueuedEvent.class.equals(systemEventClass)) {
			} else {
				super.publishEvent(facesContext, systemEventClass, source);
			}
		}
	}
	
	
}
