package com.logbookmanager.service;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.logbookmanager.domain.model.security.SecureUser;

public class RegisteredUserNotFoundException extends RegisteredUserException {
	private static final long serialVersionUID = 912839123L;

	private SecureUser secureUser;

	/**
	 * Constructor for RegisteredUserAlreadyExistsException.
	 * 
	 * @param message
	 */
	public RegisteredUserNotFoundException(String message, SecureUser secureUser) {
		super(message);
		this.setUser(secureUser);
	}

	public SecureUser getUser() {
		return secureUser;
	}

	public void setUser(SecureUser secureUser) {
		this.secureUser = secureUser;
	}

	@Override
	public String getMessage() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append(super.getMessage()).append(getUser().toString())
				.toString();
	}

}
