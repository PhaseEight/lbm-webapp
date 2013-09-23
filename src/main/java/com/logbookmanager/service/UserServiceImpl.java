package com.logbookmanager.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.logbookmanager.data.repository.UserRepository;
import com.logbookmanager.domain.model.security.User;
import com.logbookmanager.domain.support.UserName;
import com.logbookmanager.service.support.GenericService;

@Transactional(readOnly = true)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserServiceImpl extends GenericService<User, Long> implements
		UserService {
	
	private static final long serialVersionUID = 1L;

	
	@Inject
	@Qualifier("userRepository")
	private UserRepository userRepository = null;

	public UserServiceImpl(@Qualifier("userRepository") UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}

	public UserRepository getUserRepository() {
		return this.userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional(readOnly = false, isolation = Isolation.READ_UNCOMMITTED)
	@Cacheable("createdUserCache")
	public User createUser(User user) throws UserAlreadyExistsException {
		if (user == null || user.getUsername() == null) {
			return null;
		}
		User existingUser = userRepository.findByNaturalId(user);
		if (existingUser == null) { // create the user
			if(log.isInfoEnabled()){
				log.info("Creating new user: " + user.toString());
			}
			user = userRepository.saveUser(user);
		} else {
			throw new UserAlreadyExistsException(getResourcesUtil()
					.getProperty("error.user.already.exists",
							"user already exists",
							new Object[] { user.getUsername() }), existingUser,
					user);
		}
		return user;
	}

	@Transactional(readOnly = true)
	public User findUserByUsername(final UserName username) {
		final User user = new User(username);
		return userRepository.findUniqueByExample(user);
	}

	@Transactional(readOnly = true)
	public User findActiveUserByUsername(final UserName username) {
		final User user = new User(username);
		return findActiveUser(user);
	}

	/**
	 * Find Active Users
	 */
	@Transactional(readOnly = true)
	public User findActiveUser(final User user) {
		user.setActive(new Boolean(true));
		user.setDeleted(new Boolean(false));
		return userRepository.findUniqueByExample(user);
	}

	public User findUser(User user) {
		return userRepository.getUser(user);
	}

	@Transactional(readOnly = false)
	public User removeUser(User user) {
		if (user == null || user.getUsername() == null) {
			return null;
		}
		User userToDelete = userRepository.findByNaturalId(user);
		if (userToDelete == null) {
			return null;
		}
		return userRepository.removeUser(user);
	}
}
