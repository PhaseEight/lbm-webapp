package com.logbookmanager.data.repository;

import com.logbookmanager.domain.model.security.RegisteredUser;
import com.logbookmanager.domain.support.UserName;

public interface UserRepository extends Repository<RegisteredUser, Long> {

	/**
	 * Gets users information based on login name.
	 * 
	 * @param username
	 *            the current username
	 * @return user populated user object
	 */
	public RegisteredUser getActiveUserByUsername(UserName username);

	public RegisteredUser getUserByUsername(UserName username);

	public RegisteredUser getActiveUser(RegisteredUser registeredUser);

	public RegisteredUser getUser(RegisteredUser registeredUser);

	/**
	 * Saves a user's information
	 * 
	 * @param registeredUser
	 *            the object to be saved
	 */
	public RegisteredUser saveUser(RegisteredUser registeredUser);

	/**
	 * Removes a user from the database by exmple
	 * 
	 * @param username
	 *            the user's username
	 */
	public RegisteredUser removeUser(RegisteredUser registeredUser);

}
