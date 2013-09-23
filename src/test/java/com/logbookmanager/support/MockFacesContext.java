package com.logbookmanager.support;

import javax.faces.context.FacesContext;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public abstract class MockFacesContext extends FacesContext {
	private MockFacesContext() {
	}

	private static final Release RELEASE = new Release();

	private static class Release implements Answer<Void> {
		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			setCurrentInstance(null);
			return null;
		}
	}

	public static FacesContext mockFacesContext() {
		FacesContext context = mock(FacesContext.class);
		setCurrentInstance(context);
		Mockito.doAnswer(RELEASE).when(context).release();
		return context;
	}
}