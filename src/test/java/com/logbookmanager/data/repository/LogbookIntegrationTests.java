package com.logbookmanager.data.repository;

import com.logbookmanager.data.repository.LogbookRepository;
import com.logbookmanager.domain.model.logbook.Logbook;
import com.logbookmanager.domain.model.logbook.LogbookUser;
import com.logbookmanager.domain.support.Title;
import com.logbookmanager.support.IntegrationTestSupport;
import org.hibernate.SessionFactory;
import org.junit.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LogbookIntegrationTests extends IntegrationTestSupport {

    /**
     * Inject Services
     */
    @Inject
    private LogbookRepository logbookRepository;

    /**
     * Inject session Factory for flushing
     */
    @Inject
    private SessionFactory sessionFactory;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }

    @Before
    public void setup() {
    }

    @After
    public void tearDown() throws Exception {
        logger.debug("We're tearing it down!");
    }

    @Test
    @Transactional
    @Rollback(false)
    public void addLogbook() throws Throwable {
        Title title = new Title("coarse_fishing", "Coarse Fishing");
        Logbook logbook = new Logbook(title);
        Logbook newLogbook = addLogbook(logbook);
        assertTrue("The logbook title code must be coarse_fishing",
                newLogbook.getTitle().getCode().equals(logbook.getTitle().getCode()));
        assertTrue("The logbook title code must be coarse_fishing",
                newLogbook.getTitle().getCode().equals(title.getCode()));
        assertTrue("The logbook title code must be coarse_fishing",
                logbook.getTitle().getCode().equals(newLogbook.getTitle().getCode()));
    }

    @Transactional
    @Rollback(false)
    private Logbook addLogbook(Logbook logbook) throws Throwable {
        Logbook newlogbook = logbookRepository.makePersistent(logbook);
        sessionFactory.getCurrentSession().flush();
        return newlogbook;
    }

    @Test
    @Transactional
    public void listLogbooks() throws Throwable {
        List<Logbook> logbooks = logbookRepository.findAll();
        assertNotNull(logbooks);
        assertTrue("there must be more than 1 logbook after creating a logbook", logbooks.size() >= 1);
    }

    @BeforeTransaction
    /*
     * Start the test by making sure all the users in the database are set as
     * Active and NOT deleteds
     */
    public void beforeTransaction() {
        String sql = "select id, version, active, LAST_UPDATE_DATE as luts, name as title_name, code as title_code from Logbook";
        List<Logbook> logbooks = jdbcTemplate.query(sql, new RowMapper<Logbook>() {
                    public Logbook mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Title title = new Title(rs.getString("title_code"), rs.getString("title_name"));
                        Logbook l = new Logbook(title);
                        l.setId(rs.getLong("id"));
                        l.setVersion(rs.getLong("version"));
                        l.setlastUpdateTimeStamp(rs.getTimestamp("luts"));
                        l.setActive(rs.getBoolean("active"));
                        return l;
                    }
                }

        );
        for (Logbook logbook : logbooks) {
            logger.debug(logbook.toString());
        }
        assertNotNull("There must be some logbooks", logbooks);
    }

    @Test
    @Transactional
    public void findAllLogbookPeople() {
        List<LogbookUser> people = logbookRepository.listLogobookUsers();
        assertTrue("There should be 2 people ", people.size() == 2);
    }

    @AfterTransaction
    public void afterTransaction() {
        logger.debug("Transaction Completed");
    }

    public LogbookRepository getLogbookRepository() {
        return logbookRepository;
    }

    public void setLogbookRepository(LogbookRepository logbookRepository) {
        this.logbookRepository = logbookRepository;
    }

}
