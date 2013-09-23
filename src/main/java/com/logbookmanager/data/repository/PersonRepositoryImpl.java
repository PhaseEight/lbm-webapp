package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.person.Person;

@Repository
public class PersonRepositoryImpl extends HibernateRepository<Person, Long>
		implements PersonRepository, Serializable {

	private static final long serialVersionUID = 912839123L;

	public PersonRepositoryImpl() {
	}

}
