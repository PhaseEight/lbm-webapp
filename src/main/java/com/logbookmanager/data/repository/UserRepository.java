package com.logbookmanager.data.repository;

import com.logbookmanager.domain.model.security.SecureUser;
import com.logbookmanager.domain.support.UserName;

public interface UserRepository extends Repository<SecureUser, Long> {

	/**
	 * Gets users information based on login name.
	 * 
	 * @param username
	 *            the current username
	 * @return user populated user object
	 */
	public SecureUser getActiveUserByUsername(UserName username);

	public SecureUser getUserByUsername(UserName username);

	public SecureUser getActiveUser(SecureUser secureUser);

	public SecureUser getUser(SecureUser secureUser);

	/**
	 * Saves a user's information
	 * 
	 * @param secureUser
	 *            the object to be saved
	 */
	public SecureUser saveUser(SecureUser secureUser);

	/**
	 * Removes a user from the database by exmple
	 * 
	 * @param username
	 *            the user's username
	 */
	public SecureUser removeUser(SecureUser secureUser);

}
