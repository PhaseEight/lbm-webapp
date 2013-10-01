package com.logbookmanager.domain.model.security;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.NaturalId;

import com.logbookmanager.annotations.DeleteType;
import com.logbookmanager.annotations.DeletionType;
import com.logbookmanager.domain.model.logbook.LogbookUser;
import com.logbookmanager.domain.support.EntitySupport;
import com.logbookmanager.domain.support.Password;
import com.logbookmanager.domain.support.PasswordHint;
import com.logbookmanager.domain.support.UserDetails;
import com.logbookmanager.domain.support.UserName;

/**
 * RegisteredUser class
 * 
 * This class is used to generate Spring Validation rules as well as the
 * 
 * <p>
 * <a href="RegisteredUser.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 * 
 */
/*
 * @Entity
 * @Table(name = "RegisteredUser")
*/
@DeleteType(type = DeletionType.LogicalDelete)
public class RegisteredUser extends EntitySupport<com.logbookmanager.domain.model.security.RegisteredUser, Long> implements Serializable {

	private static final long serialVersionUID = 912839123L;

	private Log log = LogFactory.getLog(RegisteredUser.class);

	@NaturalId
	private UserName username;

	private Password password;

	private PasswordHint passwordHint;

	private UserDetails userDetails;

	protected Set<com.logbookmanager.domain.model.security.Role> roles = null;

	private LogbookUser logbookUser = null;

	public RegisteredUser() {
		log.info("empty user constructor");
	}

	public RegisteredUser(UserName username) {
		this(username, true, false);
	}

	public RegisteredUser(UserName username, Boolean active, Boolean deleted) {
		Validate.notNull(username, "error.username.null");
		setUsername(username);
		setActive(active);
		setDeleted(deleted);
	}

	/**
	 * Create a new user that will probably be persisted to the database
	 * 
	 * @param username
	 * @param password
	 * @param passwordHintQuestion
	 * @param passwordConfirmation
	 * @param userDetails
	 * @param roles
	 */
	public RegisteredUser(UserName username, Password password, PasswordHint passwordHint, UserDetails userDetails, Set<Role> roles) {
		this(username, password, passwordHint, userDetails, roles, true, false);
	}

	public RegisteredUser(UserName username, Password password, PasswordHint passwordHint, UserDetails userDetails, Set<Role> roles,
			Boolean active, Boolean deleted) {
		this(username, password, passwordHint, userDetails, active, deleted);
		this.setRoles(roles);
	}

	private RegisteredUser(UserName username, Password password, PasswordHint passwordHint, UserDetails userDetails, Boolean active,
			Boolean deleted) {
		Validate.notNull(username, "error.username.null");
		Validate.notNull(password, "error.password.null");

		this.setUsername(username);

		this.setPassword(password);

		this.setPasswordHint(passwordHint);
		this.setUserDetails(userDetails);

		this.active = (active == null) ? true : active;
		this.deleted = (deleted == null) ? false : deleted;

	}

	/**
	 * Returns the username.
	 * 
	 * @return String
	 * 
	 */
	public UserName getUsername() {
		return username;
	}

	/**
	 * Returns the password.
	 * 
	 * @return String
	 * 
	 * 
	 */
	public Password getPassword() {
		return password;
	}

	/**
	 * Returns the user's roles.
	 * 
	 * @return Set
	 * 
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * Adds a role for the user
	 * 
	 * @param role
	 */
	public void addRole(Role role) {
		if (log.isDebugEnabled()) {
			log.debug("adding role to user");
		}
		roles.add(role);
	}

	/**
	 * Sets the username.
	 * 
	 * @param username
	 *            The username to set
	 * @t-spring.validator type="required"
	 */
	public void setUsername(UserName username) {
		this.username = username;
	}

	/**
	 * Sets the roles.
	 * 
	 * @param roles
	 *            The roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof RegisteredUser))
			return false;

		final RegisteredUser registeredUser = (RegisteredUser) o;

		if (getUsername() != null ? !getUsername().equals(registeredUser.getUsername()) : registeredUser.getUsername() != null) {
			return false;
		} else {

			return true;
		}
	}

	@Override
	public int hashCode() {
		return (getUsername() != null ? getUsername().hashCode() : 0);
	}

	/**
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("roles", this.roles)
				.append("username", this.getUsername())
				.append("userDetails", (getUserDetails() != null) ? this.getUserDetails().toString() : "[...]")
				.append("active", this.isActive()).toString();
	}

	protected void setPassword(Password password) {
		this.password = password;
	}

	protected PasswordHint getPasswordHint() {
		return passwordHint;
	}

	protected void setPasswordHint(PasswordHint passwordHint) {
		this.passwordHint = passwordHint;
	}

	public LogbookUser getLogbookUser() {
		return logbookUser;
	}

	public void setLogbookUser(LogbookUser logbookUser) {
		this.logbookUser = logbookUser;
	}

}
