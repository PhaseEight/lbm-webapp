package com.logbookmanager.service;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;

import com.logbookmanager.data.repository.LogbookRepository;
import com.logbookmanager.data.repository.PersonRepository;
import com.logbookmanager.domain.model.logbook.Logbook;
import com.logbookmanager.domain.model.person.Person;
import com.logbookmanager.service.support.GenericService;

/**
 * Class responsible for management of Logbooks.
 * 
 * Will manage logbook layouts.
 * 
 * Create attributes for a detail, create the details themselves. Create pages
 * and place details on the page. Allow managing lists and grouping of details
 * 
 * @author Peter
 * 
 */
public class LogbookServiceImpl extends GenericService<Logbook, Long> implements
		LogbookService {

	@Inject
	Logger logger = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@Qualifier("logbookRepository")
	private LogbookRepository logbookRepository;

	@Inject
	private PersonRepository personRepository;

	@Inject
	public LogbookServiceImpl(LogbookRepository logbookRepository) {
		super(logbookRepository);
	}

	public LogbookRepository getLogbookRepository() {
		return logbookRepository;
	}

	public void setLogbookRepository(LogbookRepository logbookRepository) {
		this.logbookRepository = logbookRepository;
	}

	public PersonRepository getPersonRepository() {
		return personRepository;
	}

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	/**
	 * 
	 * @param person
	 *            the Person signing up for a new logbook
	 * @param logbook
	 * 
	 *            The logbook the person wants
	 * 
	 *            session.buildLockRequest().setLockMode(LockMode.
	 *            PESSIMISTIC_WRITE).setTimeOut(60000).lock(entity);
	 */
	public void createLogbook(Person person, Logbook logbook) {
		person.addLogbook(logbook);

		personRepository.makePersistent(person);
	}

}