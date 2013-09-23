package com.logbookmanager.data.repository;

import java.util.List;

import com.logbookmanager.domain.model.logbook.Logbook;
import com.logbookmanager.domain.model.person.Person;

public interface LogbookRepository extends Repository<Logbook, Long> {

	public List<Person> listLogobookUsers();
	
}
