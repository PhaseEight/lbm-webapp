package com.logbookmanager.web.authentication;

public class IncorrectLoginCredentialsException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Incorrect username and password combination provided";
	}

	@Override
	public String getLocalizedMessage() {
		// TODO let there be locale
		return super.getLocalizedMessage();
	}

}