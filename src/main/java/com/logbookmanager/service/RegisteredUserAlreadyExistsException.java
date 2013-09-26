package com.logbookmanager.service;

import com.logbookmanager.domain.model.security.SecureUser;

/**
 * An exception that is thrown by classes wanting to trap unique constraint
 * violations. This is used to wrap Spring's DataIntegrityViolationException so
 * it's checked in the web layer.
 * 
 * <p>
 * <a href="RegisteredUserAlreadyExistsException.java.html"><i>View Source</i></a>
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
	private SecureUser existingUser;
	private SecureUser newUser;

	public RegisteredUserAlreadyExistsException(String message, SecureUser existingUser,
			SecureUser newUser) {
		super(message);
		this.setExistingUser(existingUser);
		this.setNewUser(newUser);
	}

	public SecureUser getExistingUser() {
		return existingUser;
	}

	public void setExistingUser(SecureUser existingUser) {
		this.existingUser = existingUser;
	}

	public SecureUser getNewUser() {
		return newUser;
	}

	public void setNewUser(SecureUser newUser) {
		this.newUser = newUser;
	}

}
