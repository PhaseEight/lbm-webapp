package com.logbookmanager.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.logbookmanager.data.repository.LogbookUserRepository;
import com.logbookmanager.data.repository.Repository;
import com.logbookmanager.domain.model.logbook.LogbookUser;
import com.logbookmanager.service.support.GenericService;

/**
 * Implementation of LogbookUserService interface. </p>
 * 
 * <p>
 * <a href="LogbookUserServiceImpl.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 */
@Service("LogbookUserService")
public class LogbookUserServiceImpl extends GenericService<LogbookUser, Long> implements
		LogbookUserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@Qualifier("logbookUserRepository")
	LogbookUserRepository logbookUserRepository;

	public LogbookUserServiceImpl(Repository<LogbookUser, Long> LogbookUserRepository) {
		super(LogbookUserRepository);
	}

	@Override
	@Secured("ROLE_ADMIN")
	public List<LogbookUser> findAll() {
		return super.findAll();
	}
	

}
