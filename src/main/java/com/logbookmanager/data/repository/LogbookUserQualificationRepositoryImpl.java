package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.logbook.LogbookUserQualification;

@Repository
public class LogbookUserQualificationRepositoryImpl extends
		HibernateRepository<LogbookUserQualification, Long> implements
		LogbookUserQualificationRepository, Serializable {

	private static final long serialVersionUID = 912839123L;

	public LogbookUserQualificationRepositoryImpl() {
	}
}
