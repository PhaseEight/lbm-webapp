package com.logbookmanager.web.component;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import com.logbookmanager.web.faces.FacesContextBroker;

public class DashboardBean implements Serializable {

	/**
	 * http://www.developer.am/primefaces/?page=State
	 */
	private static final long serialVersionUID = 2L;
	private DashboardModel model;

	private FacesContextBroker facesContextBroker;

	public DashboardBean() {
		this(new FacesContextBroker());
	}

	public DashboardBean(FacesContextBroker facesContextBroker) {
		model = new DefaultDashboardModel();

		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();
		DashboardColumn column3 = new DefaultDashboardColumn();

		column1.addWidget("recentDives");
		column1.addWidget("kitBag");

		column2.addWidget("topCreatures");
		column2.addWidget("topDiveSites");

		column3.addWidget("lifestyle");
		column3.addWidget("weather");

		model.addColumn(column1);
		model.addColumn(column2);
		model.addColumn(column3);

		this.facesContextBroker = facesContextBroker;

	}

	/*
	 * @Event notes senderColumnIndex is the column that has lost the widget
	 * columnIndex is the column on which the widget was dropped widgetId is the
	 * "name" of the widget itemIndex is the 0 based "row" in the column in
	 * which the item is being dropped
	 * 
	 * @See
	 * http://static.springsource.org/spring-webflow/docs/2.0.x/reference/html
	 * /ch12s07.html
	 */
	public void handleReorder(DashboardReorderEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Moved: " + event.getSenderColumnIndex());
		message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex()
				+ ", Sender index: " + event.getSenderColumnIndex());

		DashboardColumn selectedColumn = model.getColumn(event.getColumnIndex());
		String movedWidget = selectedColumn.getWidget(event.getItemIndex());

		message.setDetail(message.getDetail() + "; movedWidget:" + movedWidget);

		// this will become the state persistence call
		addMessage(message);

	}

	private void addMessage(FacesMessage message) {
		FacesContext context = facesContextBroker.getContext();
		context.addMessage(null, message);
	}

	public DashboardModel getModel() {
		return model;
	}

}