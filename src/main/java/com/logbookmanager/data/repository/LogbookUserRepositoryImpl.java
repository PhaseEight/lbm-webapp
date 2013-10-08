package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.logbook.LogbookUser;

@Repository
public class LogbookUserRepositoryImpl extends HibernateRepository<LogbookUser, Long> implements LogbookUserRepository,
		Serializable {

	private static final long serialVersionUID = 912839123L;

	public LogbookUserRepositoryImpl() {
	}

}
