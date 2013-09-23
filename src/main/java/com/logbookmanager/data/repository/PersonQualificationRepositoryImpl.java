package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.person.PersonQualification;

@Repository
public class PersonQualificationRepositoryImpl extends
		HibernateRepository<PersonQualification, Long> implements
		PersonQualificationRepository, Serializable {

	private static final long serialVersionUID = 912839123L;

	public PersonQualificationRepositoryImpl() {
	}
}
