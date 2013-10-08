package com.logbookmanager.data.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.logbook.Logbook;
import com.logbookmanager.domain.model.logbook.LogbookUser;
import com.logbookmanager.domain.model.logbook.LogbookUserLogbook;

@Repository
public class LogbookRepositoryImpl extends HibernateRepository<Logbook, Long> implements LogbookRepository,
		Serializable {

	private static final long serialVersionUID = 912839123L;

	public LogbookRepositoryImpl() {
	}

	@Override
	// This would crash any system if there were any reason to retrieve all
	// users of all logbooks
	// Only the people using the current logbook are listed; which could still
	// bring the system down; so this would have to be paged
	public List<LogbookUser> listLogobookUsers() {
		List<Logbook> all = findAll();
		List<LogbookUser> allPeople = new ArrayList<LogbookUser>();
		for (Logbook logbook : all) {
			Set<LogbookUserLogbook> list = logbook.getLogbookUserLogbooks();
			for (LogbookUserLogbook pl : list) {
				allPeople.add(pl.getLogbookUser());
			}
		}
		return allPeople;
	}
}
