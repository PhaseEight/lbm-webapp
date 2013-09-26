package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.logbook.EntryAttribute;

@Repository
public class EntryAttributeRepositoryImpl extends
		HibernateRepository<EntryAttribute, Long> implements EntryAttributeRepository,
		Serializable {

	private static final long serialVersionUID = 912839123L;

	public EntryAttributeRepositoryImpl() {
	}
}
