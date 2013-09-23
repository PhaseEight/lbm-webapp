package com.logbookmanager.domain.model.security;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.NaturalId;

import com.logbookmanager.annotations.DeleteType;
import com.logbookmanager.annotations.DeletionType;
import com.logbookmanager.domain.model.person.Person;
import com.logbookmanager.domain.support.EntitySupport;
import com.logbookmanager.domain.support.Password;
import com.logbookmanager.domain.support.PasswordHint;
import com.logbookmanager.domain.support.UserDetails;
import com.logbookmanager.domain.support.UserName;

/**
 * User class
 * 
 * This class is used to generate Spring Validation rules as well as the
 * 
 * <p>
 * <a href="User.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 * 
 */
@Entity
@Table(name = "User")
@DeleteType(type = DeletionType.LogicalDelete)
public class User extends EntitySupport<com.logbookmanager.domain.model.security.User, Long> implements Serializable {

	private static final long serialVersionUID = 912839123L;

	private Log log = LogFactory.getLog(User.class);

	@NaturalId
	private UserName username;

	private Password password;

	private PasswordHint passwordHint;

	private UserDetails userDetails;

	protected Set<com.logbookmanager.domain.model.security.Role> roles = null;

	private Person person = null;

	public User() {
		log.info("empty user constructor");
	}

	public User(UserName username) {
		this(username, true, false);
	}

	public User(UserName username, Boolean active, Boolean deleted) {
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
	public User(UserName username, Password password, PasswordHint passwordHint, UserDetails userDetails, Set<Role> roles) {
		this(username, password, passwordHint, userDetails, roles, true, false);
	}

	public User(UserName username, Password password, PasswordHint passwordHint, UserDetails userDetails, Set<Role> roles,
			Boolean active, Boolean deleted) {
		this(username, password, passwordHint, userDetails, active, deleted);
		this.setRoles(roles);
	}

	private User(UserName username, Password password, PasswordHint passwordHint, UserDetails userDetails, Boolean active,
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
		if (!(o instanceof User))
			return false;

		final User user = (User) o;

		if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null) {
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
