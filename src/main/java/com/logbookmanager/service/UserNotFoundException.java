package com.logbookmanager.service;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.logbookmanager.domain.model.security.User;

public class UserNotFoundException extends UserException {
	private static final long serialVersionUID = 912839123L;

	private User user;

	/**
	 * Constructor for UserAlreadyExistsException.
	 * 
	 * @param message
	 */
	public UserNotFoundException(String message, User user) {
		super(message);
		this.setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String getMessage() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append(super.getMessage()).append(getUser().toString())
				.toString();
	}

}
