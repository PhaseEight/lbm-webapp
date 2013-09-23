package com.logbookmanager.service;

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
public class UserException extends Exception {
	private static final long serialVersionUID = 912839123L;

	/**
	 * Constructor for UserAlreadyExistsException.
	 * 
	 * @param message
	 */
	public UserException(String message) {
		super(message);
	}
}
