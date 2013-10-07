package com.logbookmanager.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.logbookmanager.domain.model.security.RegisteredUser;
import com.logbookmanager.domain.model.security.Role;
import com.logbookmanager.domain.support.Password;
import com.logbookmanager.domain.support.PasswordHint;
import com.logbookmanager.domain.support.UserDetails;
import com.logbookmanager.domain.support.UserName;
import com.logbookmanager.support.IntegrationTestSupport;

public class UserServiceIntegrationTests extends IntegrationTestSupport {

	@Inject
	private WebApplicationContext webApplicationContext;

	/** Inject Services */
	@Inject
	private RegisteredUserService registeredUserService;

	@Inject
	private RoleService roleService;
	/** Inject Services */

	/** Inject session Factory for flushing */
	@Inject
	private SessionFactory sessionFactory;

	@Before
	public void setup() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		logger.debug("We're tearing it down!");
	}

	@Test
	@Rollback(true)
	public void removeMissingUser() throws Throwable {
		RegisteredUser registeredUser = new RegisteredUser(new UserName("thepeterneil"));
		RegisteredUser deletedUser = registeredUserService.removeUser(registeredUser);
		/* Flush the session so we can make sure a Data Exception is thrown */
		sessionFactory.getCurrentSession().flush();
		assertNull("There should be no user found, but user is null", deletedUser);
	}

	@Test
	@Rollback(true)
	public void removeActiveUser() throws Throwable {
		RegisteredUser registeredUser = new RegisteredUser(new UserName("peterneil"), true, false);
		registeredUser = registeredUserService.findUser(registeredUser);
		registeredUser = registeredUserService.removeUser(registeredUser);

		sessionFactory.getCurrentSession().flush();

		RegisteredUser exampleUser = new RegisteredUser(new UserName("peterneil"), false, true);
		registeredUser = registeredUserService.findUser(exampleUser);
		assertNotNull("user expected in database: ", registeredUser);
		assertTrue("RegisteredUser is not deleted (i.e. user.isDeleted: " + registeredUser.isDeleted(), registeredUser.isDeleted());
		assertFalse("RegisteredUser is still active (i.e. user.isActive: " + registeredUser.isActive(), registeredUser.isActive());
	}

	@Test
	@Rollback(true)
	@Transactional
	public void findAllUsers() {
		List<RegisteredUser> registeredUsers = (List<RegisteredUser>) registeredUserService.findAll();
		Assert.assertTrue("there should be at least 6 security users: users.size: " + registeredUsers.size(), registeredUsers.size() >= 6);
	}

	@Test(expected = NullPointerException.class)
	public void addUserFails() throws Throwable {
		RegisteredUser newUser = addUser(new RegisteredUser(null));
		assertNull("The new RegisteredUser should not be able to be added with a Null username", newUser);
	}

	@Transactional
	private RegisteredUser addUser(RegisteredUser registeredUser) throws Throwable {
		RegisteredUser newUser = registeredUserService.createUser(registeredUser);
		sessionFactory.getCurrentSession().flush();
		return newUser;
	}

	@Test
	public void addUserSucceeds() throws Throwable {
		
		BCryptPasswordEncoder passwordEncoder = (BCryptPasswordEncoder)webApplicationContext.getBean("bcryptEncoder");
		
		HashSet<Role> roles = new HashSet<Role>();
		Role role = roleService.findRole("LOGBOOKUSER");
		assertNotNull("No logbookuser role found", role);
		roles.add(role);

		UserDetails userDetails = new UserDetails("thenewuserfirstname", "thenewusersurname",
				"thenewuser@phaseeightltd.co.uk", "01543898462", "07908708064", "http://www.phaseeightltd.co.uk", "en_GB");
		RegisteredUser registeredUser = new RegisteredUser(new UserName("thenewuser_username"),
				new Password(passwordEncoder.encode("thenewuser_password"), passwordEncoder.encode("thenewuser_password")), null, userDetails, roles);

		registeredUser.setlastUpdateTimeStamp(new Timestamp(Calendar.getInstance(LocaleContextHolder.getLocale()).getTimeInMillis()));

		RegisteredUser newUser = addUser(registeredUser);

		RegisteredUser user2 = registeredUserService.findUser(registeredUser);
		assertNotNull("RegisteredUser was just added to the database and was not found", user2);
		assertTrue("user firstname must be [thenew]", user2.getUserDetails().equals(userDetails));
		assertEquals("The 2 users should be equal by Entity", user2, newUser);

	}

	@BeforeTransaction
	/*
	 * Start the test by making sure all the users in the database are set as
	 * Active and NOT deleteds
	 */
	public void setupUserTables() {
		jdbcTemplate.execute("update SEC_USER set active=true, deleted=false");

		jdbcTemplate
				.execute("delete from SEC_USER_ROLE where USER_ID in (select ID from SEC_USER where username = 'thenewuser_username')");

		jdbcTemplate.execute("delete from SEC_USER where username='thenewuser'");

		String sql = "select * from SEC_USER where username=?";

		RegisteredUser registeredUser = (RegisteredUser) jdbcTemplate.queryForObject(sql, new Object[] { "peterneil" }, new RowMapper<RegisteredUser>() {
			public RegisteredUser mapRow(ResultSet rs, int rowNum) throws SQLException {

				PasswordHint passwordHint = new PasswordHint(rs.getString("password_hint_question"), rs
						.getString("password_hint_answer"));
				Password password = new Password(rs.getString("password"), rs.getString("password"));
				Set<Role> roles = Collections.emptySet();

				RegisteredUser u = new RegisteredUser(new UserName(rs.getString("username")), password, passwordHint, null, roles, rs
						.getBoolean("active"), rs.getBoolean("deleted"));

				u.setId(rs.getLong("id"));
				u.setVersion(rs.getLong("version"));
				u.setlastUpdateTimeStamp(rs.getTimestamp("last_update_date"));

				return u;
			}
		});
		assertNotNull("RegisteredUser should exist", registeredUser);
		assertFalse("RegisteredUser should NOT be deleted (i.e. deleted: false)", registeredUser.isDeleted());
		assertTrue("RegisteredUser should be active (i.e. active: true)", registeredUser.isActive());
	}

	@AfterTransaction
	public void afterTransaction() {
		logger.debug("Transaction Completed");
	}

	public WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}

	public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
		this.webApplicationContext = webApplicationContext;
	}

}
