package com.logbookmanager.service;

import java.util.List;

import com.logbookmanager.domain.model.security.RegisteredUser;
import com.logbookmanager.domain.support.UserName;
import com.logbookmanager.service.exception.RegisteredUserAlreadyExistsException;
import com.logbookmanager.service.support.DefaultService;

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
public interface RegisteredUserService extends DefaultService<RegisteredUser, Long> {

	public List<RegisteredUser> findAll();

	public RegisteredUser createUser(RegisteredUser registeredUser) throws RegisteredUserAlreadyExistsException;

	public RegisteredUser removeUser(RegisteredUser registeredUser);

	public RegisteredUser findActiveUserByUsername(UserName username);

	public RegisteredUser findUserByUsername(UserName username);

	public RegisteredUser findActiveUser(RegisteredUser registeredUser);

	public RegisteredUser findUser(RegisteredUser registeredUser);
}
