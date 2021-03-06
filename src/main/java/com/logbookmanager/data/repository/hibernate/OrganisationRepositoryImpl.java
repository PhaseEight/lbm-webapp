package com.logbookmanager.data.repository.hibernate;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.logbookmanager.data.repository.OrganisationRepository;
import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.organisation.Organisation;

@Repository
public class OrganisationRepositoryImpl extends HibernateRepository<Organisation, Long> implements
		OrganisationRepository, Serializable {

	private static final long serialVersionUID = 912839123L;

	public OrganisationRepositoryImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
