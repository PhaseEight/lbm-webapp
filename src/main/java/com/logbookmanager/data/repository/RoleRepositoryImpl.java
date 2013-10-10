package com.logbookmanager.data.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.security.Role;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Role objects.
 * 
 * <p>
 * <a href="RoleRepositoryImpl.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
@Repository
public class RoleRepositoryImpl extends HibernateRepository<Role, Long> implements RoleRepository {

	public RoleRepositoryImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Role findRole(String rolename) {
		return findByNaturalId(new Role(rolename));
	}

}
