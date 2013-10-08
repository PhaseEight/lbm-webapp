package com.logbookmanager.domain.model.logbook;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.logbookmanager.domain.model.organisation.Certification;
import com.logbookmanager.domain.model.security.RegisteredUser;
import com.logbookmanager.domain.support.EntitySupport;
import com.logbookmanager.domain.support.PersonalDetails;

/**
 * LogbookUser
 */
public class LogbookUser extends EntitySupport<LogbookUser, Long> implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 912839123L;

	// member entities
	private RegisteredUser registeredUser;

	private Set<Certification> certifications = new LinkedHashSet<Certification>();

	private Set<LogbookUserLogbook> logbookUserLogbooks = new LinkedHashSet<LogbookUserLogbook>();

	private PersonalDetails personalDetails;

	// Constructors
	/** default constructor required by Hibernate */
	LogbookUser() {
	}

	/**
	 *
	 */
	public Set<Certification> getCertifications() {
		return this.certifications;
	}

	public void setCertifications(Set<Certification> certifications) {
		this.certifications = certifications;
	}

	/**
	 *
	 */
	public Set<LogbookUserLogbook> getLogbookUserLogbooks() {
		return this.logbookUserLogbooks;
	}

	public void setLogbookUserLogbooks(Set<LogbookUserLogbook> logbookUserLogbooks) {
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

	public Certification addCertification(Certification certification) {
		if (!certifications.contains(certification)) {
			certifications.add(certification);
		}
		return certification;
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("\ncertifications", this.certifications).append("\nid", this.id)
				.append("\nlogbookUserLogbooks", this.logbookUserLogbooks).append("\nversion", this.version)
				.append("\nuser", this.registeredUser).toString();
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
		return new EqualsBuilder().append(this.registeredUser, rhs.registeredUser).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1471500779, -1755482979).append(this.registeredUser).toHashCode();
	}
}