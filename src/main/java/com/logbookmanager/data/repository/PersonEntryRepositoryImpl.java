package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.person.PersonEntry;

@Repository
public class PersonEntryRepositoryImpl extends
		HibernateRepository<PersonEntry, Long> implements PersonEntryRepository,
		Serializable {

	private static final long serialVersionUID = 912839123L;

	public PersonEntryRepositoryImpl() {
	}
}
