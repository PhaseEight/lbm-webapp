package com.logbookmanager.web.component;

import java.io.Serializable;

public class ColourPreferences implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String foreground, background;

	public String getForeground() {
		return (foreground);
	}

	public void setForeground(String foreground) {
		this.foreground = foreground;
	}

	// getBackground and setBackground
	public String getStyle() {
		String style = String.format("Colour: %s; background-Colour: %s", foreground, background);
		return (style);
	}
}