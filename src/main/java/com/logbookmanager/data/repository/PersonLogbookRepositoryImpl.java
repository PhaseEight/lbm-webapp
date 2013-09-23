package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.person.PersonLogbook;

@Repository
public class PersonLogbookRepositoryImpl extends
		HibernateRepository<PersonLogbook, Long> implements PersonLogbookRepository,
		Serializable {

	private static final long serialVersionUID = 912839123L;

	public PersonLogbookRepositoryImpl() {
	}
}
