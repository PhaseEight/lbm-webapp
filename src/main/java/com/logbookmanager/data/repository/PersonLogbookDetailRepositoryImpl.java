package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.person.PersonLogbookDetail;

@Repository
public class PersonLogbookDetailRepositoryImpl extends
		HibernateRepository<PersonLogbookDetail, Long> implements
		PersonLogbookDetailRepository, Serializable {

	private static final long serialVersionUID = 912839123L;

	public PersonLogbookDetailRepositoryImpl() {
	}
}
