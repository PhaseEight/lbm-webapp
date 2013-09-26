package com.logbookmanager.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.logbookmanager.data.repository.UserRepository;
import com.logbookmanager.domain.model.security.SecureUser;
import com.logbookmanager.domain.support.UserName;
import com.logbookmanager.service.support.GenericService;

@Transactional(readOnly = true)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class RegisteredUserServiceImpl extends GenericService<SecureUser, Long> implements
		RegisteredUserService {
	
	private static final long serialVersionUID = 1L;

	
	@Inject
	@Qualifier("userRepository")
	private UserRepository userRepository = null;

	public RegisteredUserServiceImpl(@Qualifier("userRepository") UserRepository userRepository) {
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
	public SecureUser createUser(SecureUser secureUser) throws RegisteredUserAlreadyExistsException {
		if (secureUser == null || secureUser.getUsername() == null) {
			return null;
		}
		SecureUser existingUser = userRepository.findByNaturalId(secureUser);
		if (existingUser == null) { // create the user
			if(log.isInfoEnabled()){
				log.info("Creating new user: " + secureUser.toString());
			}
			secureUser = userRepository.saveUser(secureUser);
		} else {
			throw new RegisteredUserAlreadyExistsException(getResourcesUtil()
					.getProperty("error.user.already.exists",
							"user already exists",
							new Object[] { secureUser.getUsername() }), existingUser,
					secureUser);
		}
		return secureUser;
	}

	@Transactional(readOnly = true)
	public SecureUser findUserByUsername(final UserName username) {
		final SecureUser secureUser = new SecureUser(username);
		return userRepository.findUniqueByExample(secureUser);
	}

	@Transactional(readOnly = true)
	public SecureUser findActiveUserByUsername(final UserName username) {
		final SecureUser secureUser = new SecureUser(username);
		return findActiveUser(secureUser);
	}

	/**
	 * Find Active Users
	 */
	@Transactional(readOnly = true)
	public SecureUser findActiveUser(final SecureUser secureUser) {
		secureUser.setActive(new Boolean(true));
		secureUser.setDeleted(new Boolean(false));
		return userRepository.findUniqueByExample(secureUser);
	}

	public SecureUser findUser(SecureUser secureUser) {
		return userRepository.getUser(secureUser);
	}

	@Transactional(readOnly = false)
	public SecureUser removeUser(SecureUser secureUser) {
		if (secureUser == null || secureUser.getUsername() == null) {
			return null;
		}
		SecureUser userToDelete = userRepository.findByNaturalId(secureUser);
		if (userToDelete == null) {
			return null;
		}
		return userRepository.removeUser(secureUser);
	}
}
