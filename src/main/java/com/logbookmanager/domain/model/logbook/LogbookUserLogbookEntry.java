package com.logbookmanager.domain.model.logbook;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.logbookmanager.domain.support.EntitySupport;

public class LogbookUserLogbookEntry extends EntitySupport<LogbookUserLogbookEntry, Long> implements
		java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	// Fields

	// which logbookUserlogbook this entry belongs to, we can navigate to the logbookUser
	// from their logbook
	private LogbookUserLogbook logbookUserLogbook;

	// hold the logbookUser to prevent the need to load the logbookUser logbook just to
	// retrieve the user this entry belongs to
	private LogbookUser logbookUser;

	private Integer entryNumber;

	private Set<EntryAttribute> entryAttributes = new HashSet<EntryAttribute>();

	// Constructors

	/** default constructor required by Hibernate */
	LogbookUserLogbookEntry() {
	}

	/**
	 * 
	 */
	public LogbookUserLogbook getLogbookUserLogbook() {
		return this.logbookUserLogbook;
	}

	public void setLogbookUserLogbook(LogbookUserLogbook logbookUserLogbook) {
		this.logbookUserLogbook = logbookUserLogbook;
	}

	/**
	 * 
	 */
	public LogbookUser getLogbookUser() {
		return this.logbookUser;
	}

	public void setLogbookUser(LogbookUser logbookUser) {
		this.logbookUser = logbookUser;
	}

	/**
	 * 
	 */
	public Integer getEntryNumber() {
		return this.entryNumber;
	}

	public void setEntryNumber(Integer entryNumber) {
		this.entryNumber = entryNumber;
	}

	/**
	 * 
	 */
	public Set<EntryAttribute> getEntryAttributes() {
		return this.entryAttributes;
	}

	public void setEntryAttributes(Set<EntryAttribute> entryAttributes) {
		this.entryAttributes = entryAttributes;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof LogbookUserLogbookEntry)) {
			return false;
		}
		LogbookUserLogbookEntry rhs = (LogbookUserLogbookEntry) object;
		return new EqualsBuilder().append(this.entryNumber, rhs.entryNumber)
				.append(this.id, rhs.id).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-886008693, -818375109)
				.append(this.entryNumber).append(this.id).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("entryNumber", this.entryNumber).toString();
	}

}