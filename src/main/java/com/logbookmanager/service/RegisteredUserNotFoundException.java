package com.logbookmanager.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.logbookmanager.domain.model.security.RegisteredUser;

public class RegisteredUserNotFoundException extends RegisteredUserException {
	private static final long serialVersionUID = 912839123L;

	private RegisteredUser registeredUser;

	/**
	 * Constructor for RegisteredUserAlreadyExistsException.
	 * 
	 * @param message
	 */
	public RegisteredUserNotFoundException(String message, RegisteredUser registeredUser) {
		super(message);
		this.setUser(registeredUser);
	}

	public RegisteredUser getUser() {
		return registeredUser;
	}

	public void setUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	@Override
	public String getMessage() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append(super.getMessage())
				.append(getUser().toString()).toString();
	}

}
