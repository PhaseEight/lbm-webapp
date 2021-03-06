package com.logbookmanager.service.exception;

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
public class RegisteredUserException extends Exception {
	private static final long serialVersionUID = 912839123L;

	/**
	 * Constructor for RegisteredUserAlreadyExistsException.
	 * 
	 * @param message
	 */
	public RegisteredUserException(String message) {
		super(message);
	}
}
