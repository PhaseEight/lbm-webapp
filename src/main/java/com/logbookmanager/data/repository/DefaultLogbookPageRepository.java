package com.logbookmanager.data.repository;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultLogbookPageRepository  implements LogbookPageRepository, Serializable {

	private static final long serialVersionUID = 912839123L;
	Repository

	public DefaultLogbookPageRepository(SessionFactory sessionFactory) {
	}
	
}
