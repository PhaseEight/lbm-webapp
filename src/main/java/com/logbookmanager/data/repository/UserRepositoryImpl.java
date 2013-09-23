package com.logbookmanager.data.repository;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.security.User;
import com.logbookmanager.domain.support.UserName;

@Repository
public class UserRepositoryImpl extends HibernateRepository<User, Long> implements
		UserRepository {

	/**
	 * the super must be called otherwise the Generics do not work
	 */
	public UserRepositoryImpl() {
		super(User.class, Long.class);
	}

	/**
	 */
	public User getUserByUsername(UserName username) {
		User eg = new User(username);
		return getUser(eg);
	}

	public User getActiveUserByUsername(UserName username) {
		User eg = new User(username);
		eg.setActive(true);
		return getUser(eg);
	}

	public User getActiveUser(User user) {
		User result = null;
		user.setActive(true);
		result = getUser(user);
		return result;
	}

	public User getUser(User user) {
		User result = findUniqueByExample(user);
		return result;
	}

	/**
	 */
	public User saveUser(User user) {
		if (this.log.isDebugEnabled()) {
			this.log.debug("user's username: " + user.getUsername());
		}
		makePersistent(user);

		// we have to flush to enable the propogation of a
		// DataIntegrityViolation thrown by Spring.
		getSessionFactory().getCurrentSession().flush();

		return user;
	}

	/**
	 */
	public User removeUser(User user) {
		return delete(user);
	}

}
