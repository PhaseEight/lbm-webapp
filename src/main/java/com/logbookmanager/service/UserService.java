package com.logbookmanager.service;

import java.util.List;

import com.logbookmanager.domain.model.security.User;
import com.logbookmanager.domain.support.UserName;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * 
 * <p>
 * <a href="UserService.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 *         Modified by <a href="mailto:peter.neil@logbookmanager.com">Peter
 *         Neil</a>
 */
public interface UserService extends LBMService<User, Long> {

	public List<User> findAll();

	public User createUser(User user) throws UserAlreadyExistsException;

	public User removeUser(User user);

	public User findActiveUserByUsername(UserName username);

	public User findUserByUsername(UserName username);

	public User findActiveUser(User user);

	public User findUser(User user);
}
