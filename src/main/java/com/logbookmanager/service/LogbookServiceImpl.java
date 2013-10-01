package com.logbookmanager.service;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;

import com.logbookmanager.data.repository.LogbookRepository;
import com.logbookmanager.data.repository.LogbookUserRepository;
import com.logbookmanager.domain.model.logbook.Logbook;
import com.logbookmanager.domain.model.logbook.LogbookUser;
import com.logbookmanager.service.support.GenericService;

/**
 * Class responsible for management of Logbooks.
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

	private LogbookRepository logbookRepository;

	private LogbookUserRepository logbookUserRepository;

	@Inject
	public LogbookServiceImpl(@Qualifier("logbookRepository") LogbookRepository logbookRepository, LogbookUserRepository logbookUserRepository) {
		super(logbookRepository);
		this.logbookUserRepository = logbookUserRepository;
	}

	public LogbookRepository getLogbookRepository() {
		return logbookRepository;
	}

	public void setLogbookRepository(LogbookRepository logbookRepository) {
		this.logbookRepository = logbookRepository;
	}

	public LogbookUserRepository getLogbookUserRepository() {
		return logbookUserRepository;
	}

	public void setLogbookUserRepository(LogbookUserRepository logbookUserRepository) {
		this.logbookUserRepository = logbookUserRepository;
	}

	/**
	 * 
	 * @param logbookUser
	 *            the LogbookUser signing up for a new logbook
	 * @param logbook
	 * 
	 *            The logbook the registered user wants to use
	 * 
	 *            session.buildLockRequest().setLockMode(LockMode.
	 *            PESSIMISTIC_WRITE).setTimeOut(60000).lock(entity);
	 */
	public void createLogbook(LogbookUser logbookUser, Logbook logbook) {
		logbookUser.addLogbook(logbook);

		logbookUserRepository.makePersistent(logbookUser);
	}

}
