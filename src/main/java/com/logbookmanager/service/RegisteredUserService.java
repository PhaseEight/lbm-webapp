package com.logbookmanager.service;

import java.util.List;

import com.logbookmanager.domain.model.security.SecureUser;
import com.logbookmanager.domain.support.UserName;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * 
 * <p>
 * <a href="RegisteredUserService.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 *         Modified by <a href="mailto:peter.neil@logbookmanager.com">Peter
 *         Neil</a>
 */
public interface RegisteredUserService extends LBMService<SecureUser, Long> {

	public List<SecureUser> findAll();

	public SecureUser createUser(SecureUser secureUser) throws RegisteredUserAlreadyExistsException;

	public SecureUser removeUser(SecureUser secureUser);

	public SecureUser findActiveUserByUsername(UserName username);

	public SecureUser findUserByUsername(UserName username);

	public SecureUser findActiveUser(SecureUser secureUser);

	public SecureUser findUser(SecureUser secureUser);
}
