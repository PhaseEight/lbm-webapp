package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.logbook.DetailAttribute;

@Repository
public class DetailAttributeRepositoryImpl extends
		HibernateRepository<DetailAttribute, Long> implements DetailAttributeRepository,
		Serializable {

	private static final long serialVersionUID = 912839123L;

	public DetailAttributeRepositoryImpl() {
	}
}
