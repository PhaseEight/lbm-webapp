package com.logbookmanager.service;

import com.logbookmanager.data.repository.UserRepository;
import com.logbookmanager.domain.model.security.RegisteredUser;
import com.logbookmanager.domain.support.UserName;
import com.logbookmanager.service.exception.RegisteredUserAlreadyExistsException;
import com.logbookmanager.service.support.GenericService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Transactional(readOnly = true)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class RegisteredUserServiceImpl extends GenericService<RegisteredUser, Long> implements RegisteredUserService {

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
    public RegisteredUser createUser(RegisteredUser registeredUser) throws RegisteredUserAlreadyExistsException {
        if (registeredUser == null || registeredUser.getUsername() == null) {
            return null;
        }
        RegisteredUser existingUser = userRepository.findByNaturalId(registeredUser);
        if (existingUser == null) { // create the user
            if (log.isInfoEnabled()) {
                log.info("Creating new user: " + registeredUser.toString());
            }
            registeredUser = userRepository.saveUser(registeredUser);
        } else {
            throw new RegisteredUserAlreadyExistsException(getResourcesUtil().getProperty("error.user.already.exists",
                    "user already exists", new Object[]{registeredUser.getUsername()}), existingUser, registeredUser);
        }
        return registeredUser;
    }

    @Transactional(readOnly = true)
    public RegisteredUser findUserByUsername(final UserName username) {
        final RegisteredUser registeredUser = new RegisteredUser(username);
        return userRepository.findOneByExample(registeredUser);
    }

    @Transactional(readOnly = true)
    public RegisteredUser findActiveUserByUsername(final UserName username) {
        final RegisteredUser registeredUser = new RegisteredUser(username);
        return findActiveUser(registeredUser);
    }

    /**
     * Find Active Users
     */
    @Transactional(readOnly = true)
    public RegisteredUser findActiveUser(final RegisteredUser registeredUser) {
        registeredUser.setActive(new Boolean(true));
        registeredUser.setDeleted(new Boolean(false));
        return userRepository.findOneByExample(registeredUser);
    }

    public RegisteredUser findUser(RegisteredUser registeredUser) {
        return userRepository.getUser(registeredUser);
    }

    @Transactional(readOnly = false)
    public RegisteredUser removeUser(RegisteredUser registeredUser) {
        if (registeredUser == null || registeredUser.getUsername() == null) {
            return null;
        }
        RegisteredUser userToDelete = userRepository.findByNaturalId(registeredUser);
        if (userToDelete == null) {
            return null;
        }
        return userRepository.removeUser(registeredUser);
    }
}
