package com.logbookmanager.faces;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;
import org.primefaces.event.DashboardReorderEvent;

import com.logbookmanager.web.component.DashboardBean;
import com.logbookmanager.web.faces.FacesContextBroker;

public class DashboardBeanTest {

	@Test
	public void testDashboardBean() {
		FacesContext context = mock(FacesContext.class);
		Map<String, Object> session = new HashMap<String, Object>();
		ExternalContext ext = mock(ExternalContext.class);
		when(ext.getSessionMap()).thenReturn(session);
		when(context.getExternalContext()).thenReturn(ext);

		FacesContextBroker broker = mock(FacesContextBroker.class);
		when(broker.getContext()).thenReturn(context);

		DashboardReorderEvent event = mock(DashboardReorderEvent.class);
		when(event.getWidgetId()).thenReturn("1");
		when(event.getItemIndex()).thenReturn(1);
		when(event.getColumnIndex()).thenReturn(1);
		when(event.getSenderColumnIndex()).thenReturn(1);

		DashboardBean bean = new DashboardBean(broker);
		bean.handleReorder(event);

		FacesMessage message = new FacesMessage() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean equals(Object o) {
				FacesMessage fm = (FacesMessage) o;
				EqualsBuilder eb = new EqualsBuilder();
				return eb.append(this.getDetail(), fm.getDetail()).append(this.getSeverity(), fm.getSeverity())
						.append(this.getSummary(), fm.getSummary()).isEquals();
			}
		};
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Moved: " + event.getSenderColumnIndex());
		message.setDetail("Item index: 1, Column index: 1, Sender index: 1; movedWidget:topDiveSites");

		verify(context, atLeastOnce()).addMessage(null, message);

		if (context != null)
			context.release();
	}
}
