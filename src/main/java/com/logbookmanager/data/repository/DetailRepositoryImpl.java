package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.logbook.Detail;

@Repository
public class DetailRepositoryImpl extends HibernateRepository<Detail, Long>
		implements DetailRepository, Serializable {

	private static final long serialVersionUID = 912839123L;

	public DetailRepositoryImpl() {
	}
}
