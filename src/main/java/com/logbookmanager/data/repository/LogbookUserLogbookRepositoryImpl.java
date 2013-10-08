package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.logbook.LogbookUserLogbook;

@Repository
public class LogbookUserLogbookRepositoryImpl extends HibernateRepository<LogbookUserLogbook, Long> implements
		LogbookUserLogbookRepository, Serializable {

	private static final long serialVersionUID = 912839123L;

	public LogbookUserLogbookRepositoryImpl() {
	}
}
