package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.logbook.LogbookUserLogbookDetail;

@Repository
public class LogbookUserLogbookDetailRepositoryImpl extends
		HibernateRepository<LogbookUserLogbookDetail, Long> implements
		LogbookUserLogbookDetailRepository, Serializable {

	private static final long serialVersionUID = 912839123L;

	public LogbookUserLogbookDetailRepositoryImpl() {
	}
}
