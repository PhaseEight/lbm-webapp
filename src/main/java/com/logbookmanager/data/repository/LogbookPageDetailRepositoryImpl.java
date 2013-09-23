package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.logbook.LogbookPageDetail;

@Repository
public class LogbookPageDetailRepositoryImpl extends
		HibernateRepository<LogbookPageDetail, Long> implements
		LogbookPageDetailRepository, Serializable {

	private static final long serialVersionUID = 912839123L;

	public LogbookPageDetailRepositoryImpl() {
	}
}
