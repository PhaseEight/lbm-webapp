package com.logbookmanager.data.repository;

import com.logbookmanager.domain.model.security.User;
import com.logbookmanager.domain.support.UserName;

public interface UserRepository extends Repository<User, Long> {

	/**
	 * Gets users information based on login name.
	 * 
	 * @param username
	 *            the current username
	 * @return user populated user object
	 */
	public User getActiveUserByUsername(UserName username);

	public User getUserByUsername(UserName username);

	public User getActiveUser(User user);

	public User getUser(User user);

	/**
	 * Saves a user's information
	 * 
	 * @param user
	 *            the object to be saved
	 */
	public User saveUser(User user);

	/**
	 * Removes a user from the database by exmple
	 * 
	 * @param username
	 *            the user's username
	 */
	public User removeUser(User user);

}
