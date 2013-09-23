package com.logbookmanager.web.component;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.springframework.stereotype.Service;

@Service
@SessionScoped
@Named("growlBean")
public class GrowlBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void save(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null,
				new FacesMessage("Successful", "Hello " + text));
		context.addMessage(null, new FacesMessage("Second Message",
				"Additional Info Here..."));
	}
}
