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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.logbookmanager.domain.model.security.Role;
import com.logbookmanager.domain.model.security.SecureUser;
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
		System.out.println("We're tearing it down!");
	}

	@Test
	@Rollback(true)
	public void removeMissingUser() throws Throwable {
		SecureUser secureUser = new SecureUser(new UserName("thepeterneil"));
		SecureUser deletedUser = registeredUserService.removeUser(secureUser);
		/* Flush the session so we can make sure a Data Exception is thrown */
		sessionFactory.getCurrentSession().flush();
		assertNull("There should be no user found, but user is null", deletedUser);
	}

	@Test
	@Rollback(true)
	public void removeActiveUser() throws Throwable {
		SecureUser secureUser = new SecureUser(new UserName("peterneil"), true, false);
		secureUser = registeredUserService.findUser(secureUser);
		secureUser = registeredUserService.removeUser(secureUser);

		sessionFactory.getCurrentSession().flush();

		SecureUser exampleUser = new SecureUser(new UserName("peterneil"), false, true);
		secureUser = registeredUserService.findUser(exampleUser);
		assertNotNull("user expected in database: ", secureUser);
		assertTrue("SecureUser is not deleted (i.e. user.isDeleted: " + secureUser.isDeleted(), secureUser.isDeleted());
		assertFalse("SecureUser is still active (i.e. user.isActive: " + secureUser.isActive(), secureUser.isActive());
	}

	@Test
	@Rollback(true)
	@Transactional
	public void findAllUsers() {
		List<SecureUser> secureUsers = (List<SecureUser>) registeredUserService.findAll();
		Assert.assertTrue("there should be at least 6 security users: users.size: " + secureUsers.size(), secureUsers.size() >= 6);
	}

	@Test(expected = NullPointerException.class)
	public void userAddFails() throws Throwable {
		SecureUser newUser = addUser(new SecureUser(null));
		assertNull("The new SecureUser should not be able to be added with a Null username", newUser);
	}

	@Transactional
	private SecureUser addUser(SecureUser secureUser) throws Throwable {
		SecureUser newUser = registeredUserService.createUser(secureUser);
		sessionFactory.getCurrentSession().flush();
		return newUser;
	}

	@Test
	@Rollback(false)
	public void addOneThousandUsers() throws Throwable {
		HashSet<Role> roles = new HashSet<Role>();
		Role role = roleService.findRole("logbookuser");
		roles.add(role);

		Long startTimestamp = Calendar.getInstance(LocaleContextHolder.getLocale()).getTimeInMillis();

		for (int i = 0; i < 1000; i++) {

			Long startCreateUser = Calendar.getInstance(LocaleContextHolder.getLocale()).getTimeInMillis();

			logger.debug("Creating user: " + 1);

			UserDetails userDetails = new UserDetails("thenewuserfirstname" + i, "thenewusersurname" + i, "thenewuser" + i
					+ "@phaseeightltd.co.uk", "01543898462", "07908708064", "http://www.phaseeightltd" + i + ".co.uk",
					"en_GB");
			SecureUser secureUser = new SecureUser(new UserName("thenewuser_username" + i), new Password("thenewuser_password" + i,
					"thenewuser_password" + i), null, userDetails, roles);

			secureUser.setlastUpdateTimeStamp(new Timestamp(Calendar.getInstance(LocaleContextHolder.getLocale())
					.getTimeInMillis()));

			addUser(secureUser);

			Long endCreateUser = Calendar.getInstance(LocaleContextHolder.getLocale()).getTimeInMillis();

			Long runtime = endCreateUser - startCreateUser;

			logger.debug("Execution time: " + runtime);

		}

		Long endTimestamp = Calendar.getInstance(LocaleContextHolder.getLocale()).getTimeInMillis();

		Long runtimeLong = endTimestamp - startTimestamp;

		System.out.println("Execution time: " + runtimeLong);

	}

	@Test
	public void userAddSucceeds() throws Throwable {
		HashSet<Role> roles = new HashSet<Role>();
		Role role = roleService.findRole("logbookuser");
		assertNotNull("No logbookuser role found", role);
		roles.add(role);

		UserDetails userDetails = new UserDetails("thenewuserfirstname", "thenewusersurname",
				"thenewuser@phaseeightltd.co.uk", "01543898462", "07908708064", "http://www.phaseeightltd.co.uk", "en_GB");
		SecureUser secureUser = new SecureUser(new UserName("thenewuser_username"),
				new Password("thenewuser_password", "thenewuser_password"), null, userDetails, roles);

		secureUser.setlastUpdateTimeStamp(new Timestamp(Calendar.getInstance(LocaleContextHolder.getLocale()).getTimeInMillis()));

		SecureUser newUser = addUser(secureUser);

		SecureUser user2 = registeredUserService.findUser(secureUser);
		assertNotNull("SecureUser was just added to the database and was not found", user2);
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

		SecureUser secureUser = (SecureUser) jdbcTemplate.queryForObject(sql, new Object[] { "peterneil" }, new RowMapper<SecureUser>() {
			public SecureUser mapRow(ResultSet rs, int rowNum) throws SQLException {

				PasswordHint passwordHint = new PasswordHint(rs.getString("password_hint_question"), rs
						.getString("password_hint_answer"));
				Password password = new Password(rs.getString("password"), rs.getString("password"));
				Set<Role> roles = Collections.emptySet();

				SecureUser u = new SecureUser(new UserName(rs.getString("username")), password, passwordHint, null, roles, rs
						.getBoolean("active"), rs.getBoolean("deleted"));

				u.setId(rs.getLong("id"));
				u.setVersion(rs.getLong("version"));
				u.setlastUpdateTimeStamp(rs.getTimestamp("last_update_date"));

				return u;
			}
		});
		assertNotNull("SecureUser should exist", secureUser);
		assertFalse("SecureUser should NOT be deleted (i.e. deleted: false)", secureUser.isDeleted());
		assertTrue("SecureUser should be active (i.e. active: true)", secureUser.isActive());
	}

	@AfterTransaction
	public void afterTransaction() {
		System.out.println("Transaction Completed");
	}

	public WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}

	public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
		this.webApplicationContext = webApplicationContext;
	}

}
