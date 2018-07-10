package com.logbookmanager.service;

import com.logbookmanager.data.repository.LogbookUserRepository;
import com.logbookmanager.data.repository.Repository;
import com.logbookmanager.domain.model.logbook.LogbookUser;
import com.logbookmanager.service.support.GenericService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of LogbookUserService interface. </p>
 *
 * <p>
 * <a href="LogbookUserServiceImpl.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 */
@Service("logbookUserService")
public class LogbookUserServiceImpl extends GenericService<LogbookUser, Long> implements LogbookUserService {

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
