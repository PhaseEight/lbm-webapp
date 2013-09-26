package com.logbookmanager.domain.model.logbook;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.logbookmanager.domain.model.organisation.Qualification;
import com.logbookmanager.domain.model.security.RegisteredUser;
import com.logbookmanager.domain.support.EntitySupport;
import com.logbookmanager.domain.support.PersonalDetails;

/**
 * LogbookUser generated by hbm2java
 */
public class LogbookUser extends EntitySupport<LogbookUser, Long> implements
		java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 912839123L;

	// member entities
	private RegisteredUser secUser;

	private List<Qualification> qualifications = new ArrayList<Qualification>();

	private List<LogbookUserLogbook> logbookUserLogbooks = new ArrayList<LogbookUserLogbook>();
	
	private PersonalDetails personalDetails;

	// Constructors
	/** default constructor required by Hibernate */
	LogbookUser() {
	}

	/**
	 *
	 */
	public List<Qualification> getQualifications() {
		return this.qualifications;
	}

	public void setQualifications(
			List<Qualification> qualifications) {
		this.qualifications = qualifications;
	}

	/**
	 *
	 */
	public List<LogbookUserLogbook> getLogbookUserLogbooks() {
		return this.logbookUserLogbooks;
	}

	public void setLogbookUserLogbooks(List<LogbookUserLogbook> logbookUserLogbooks) {
		this.logbookUserLogbooks = logbookUserLogbooks;
	}

	public void addLogbook(Logbook logbook) {
		LogbookUserLogbook logbookUserLogbook = new LogbookUserLogbook();
		logbookUserLogbook.setLogbook(logbook);
		logbookUserLogbook.setLogbookUser(this);
		if (!this.logbookUserLogbooks.contains(logbookUserLogbook)) {
			this.logbookUserLogbooks.add(logbookUserLogbook);
		}
	}

	public Qualification addQualification(Qualification qualification) {
		if (!qualifications.contains(qualification)) {
			qualifications.add(qualification);
		} else {
			return qualifications.get(qualifications.indexOf(qualification));
		}
		return null;
	}

	public RegisteredUser getSecUser() {
		return secUser;
	}

	public void setSecUser(RegisteredUser secUser) {
		this.secUser = secUser;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("\nqualifications", this.qualifications)
				.append("\nid", this.id)
				.append("\nlogbookUserLogbooks", this.logbookUserLogbooks)
				.append("\nversion", this.version)
				.append("\nuser", this.secUser).toString();
	}

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof LogbookUser)) {
			return false;
		}
		LogbookUser rhs = (LogbookUser) object;
		return new EqualsBuilder().append(this.secUser, rhs.secUser).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1471500779, -1755482979)
				.append(this.secUser).toHashCode();
	}
}