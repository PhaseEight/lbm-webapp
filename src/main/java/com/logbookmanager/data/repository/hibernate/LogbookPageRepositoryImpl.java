package com.logbookmanager.data.repository.hibernate;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.logbookmanager.data.repository.LogbookPageRepository;
import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.logbook.LogbookPage;

@Repository
public class LogbookPageRepositoryImpl extends HibernateRepository<LogbookPage, Long> implements LogbookPageRepository, Serializable {

	private static final long serialVersionUID = 912839123L;

	public LogbookPageRepositoryImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
}
