package com.logbookmanager.data.repository.hibernate;

import com.logbookmanager.data.repository.OrganisationCountryRepository;
import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.organisation.OrganisationCountry;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class OrganisationCountryRepositoryImpl extends HibernateRepository<OrganisationCountry, Long> implements
        OrganisationCountryRepository, Serializable {

    private static final long serialVersionUID = 912839123L;

    public OrganisationCountryRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
