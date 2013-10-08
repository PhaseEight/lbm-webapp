package com.logbookmanager.service;

import com.logbookmanager.domain.model.security.RegisteredUser;

/**
 * An exception that is thrown by classes wanting to trap unique constraint
 * violations. This is used to wrap Spring's DataIntegrityViolationException so
 * it's checked in the web layer.
 * 
 * <p>
 * <a href="RegisteredUserAlreadyExistsException.java.html"><i>View
 * Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 */
public class RegisteredUserAlreadyExistsException extends RegisteredUserException {
	private static final long serialVersionUID = 912839123L;

	/**
	 * Constructor for RegisteredUserAlreadyExistsException.
	 * 
	 * @param message
	 */
	private RegisteredUser existingUser;
	private RegisteredUser newUser;

	public RegisteredUserAlreadyExistsException(String message, RegisteredUser existingUser, RegisteredUser newUser) {
		super(message);
		this.setExistingUser(existingUser);
		this.setNewUser(newUser);
	}

	public RegisteredUser getExistingUser() {
		return existingUser;
	}

	public void setExistingUser(RegisteredUser existingUser) {
		this.existingUser = existingUser;
	}

	public RegisteredUser getNewUser() {
		return newUser;
	}

	public void setNewUser(RegisteredUser newUser) {
		this.newUser = newUser;
	}

}
