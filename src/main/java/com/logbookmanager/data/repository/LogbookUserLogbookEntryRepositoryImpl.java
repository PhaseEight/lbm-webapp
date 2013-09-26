package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.logbook.LogbookUserLogbookEntry;

@Repository
public class LogbookUserLogbookEntryRepositoryImpl extends
		HibernateRepository<LogbookUserLogbookEntry, Long> implements LogbookUserLogbookEntryRepository,
		Serializable {

	private static final long serialVersionUID = 912839123L;

	public LogbookUserLogbookEntryRepositoryImpl() {
	}
}
