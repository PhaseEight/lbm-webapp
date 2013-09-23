package com.logbookmanager.service;

import com.logbookmanager.domain.model.security.User;

/**
 * An exception that is thrown by classes wanting to trap unique constraint
 * violations. This is used to wrap Spring's DataIntegrityViolationException so
 * it's checked in the web layer.
 * 
 * <p>
 * <a href="UserAlreadyExistsException.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 */
public class UserAlreadyExistsException extends UserException {
	private static final long serialVersionUID = 912839123L;

	/**
	 * Constructor for UserAlreadyExistsException.
	 * 
	 * @param message
	 */
	private User existingUser;
	private User newUser;

	public UserAlreadyExistsException(String message, User existingUser,
			User newUser) {
		super(message);
		this.setExistingUser(existingUser);
		this.setNewUser(newUser);
	}

	public User getExistingUser() {
		return existingUser;
	}

	public void setExistingUser(User existingUser) {
		this.existingUser = existingUser;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

}
