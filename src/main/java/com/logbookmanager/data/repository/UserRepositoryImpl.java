package com.logbookmanager.data.repository;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.security.SecureUser;
import com.logbookmanager.domain.support.UserName;

@Repository
public class UserRepositoryImpl extends HibernateRepository<SecureUser, Long> implements
		UserRepository {

	/**
	 * the super must be called otherwise the Generics do not work
	 */
	public UserRepositoryImpl() {
		super(SecureUser.class, Long.class);
	}

	/**
	 */
	public SecureUser getUserByUsername(UserName username) {
		SecureUser eg = new SecureUser(username);
		return getUser(eg);
	}

	public SecureUser getActiveUserByUsername(UserName username) {
		SecureUser eg = new SecureUser(username);
		eg.setActive(true);
		return getUser(eg);
	}

	public SecureUser getActiveUser(SecureUser secureUser) {
		SecureUser result = null;
		secureUser.setActive(true);
		result = getUser(secureUser);
		return result;
	}

	public SecureUser getUser(SecureUser secureUser) {
		SecureUser result = findUniqueByExample(secureUser);
		return result;
	}

	/**
	 */
	public SecureUser saveUser(SecureUser secureUser) {
		if (this.log.isDebugEnabled()) {
			this.log.debug("user's username: " + secureUser.getUsername());
		}
		makePersistent(secureUser);

		// we have to flush to enable the propogation of a
		// DataIntegrityViolation thrown by Spring.
		getSessionFactory().getCurrentSession().flush();

		return secureUser;
	}

	/**
	 */
	public SecureUser removeUser(SecureUser secureUser) {
		return delete(secureUser);
	}

}
