package com.logbookmanager.data.repository;

import com.logbookmanager.domain.model.logbook.Logbook;
import com.logbookmanager.domain.model.logbook.LogbookUser;

import java.util.List;

public interface LogbookRepository extends Repository<Logbook, Long> {

    public List<LogbookUser> listLogobookUsers();

}
