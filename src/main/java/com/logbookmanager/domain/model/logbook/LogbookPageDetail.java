package com.logbookmanager.domain.model.logbook;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.logbookmanager.domain.support.EntitySupport;

/**
 * The logbook page is the aggregate of the activity that was undertaken
 * 
 * e.g. 
 * 1.) My Scuba Dive
 * 		Kit
 * 2.) My Fishing Session
 * 		Tackle
 * 
 */
public class LogbookPageDetail extends EntitySupport<LogbookPageDetail, Long>
		implements java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	// Fields
	private Detail detail;

	private LogbookPage logbookPage;

	// Constructors

	/** default constructor required by Hibernate */
	LogbookPageDetail() {
	}

	/**
	 * 
	 */
	public Detail getDetail() {
		return this.detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	/**
	 * 
	 */
	public LogbookPage getLogbookPage() {
		return this.logbookPage;
	}

	public void setLogbookPage(LogbookPage logbookPage) {
		this.logbookPage = logbookPage;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof LogbookPageDetail)) {
			return false;
		}
		LogbookPageDetail rhs = (LogbookPageDetail) object;
		return new EqualsBuilder().append(this.detail, rhs.detail)
				.append(this.id, rhs.id).append(this.version, rhs.version)
				.append(this.logbookPage, rhs.logbookPage).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1375402965, -1026115943).append(this.detail)
				.append(this.id).append(this.version).append(this.logbookPage)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("version", this.version)
				.append("logbookPage", this.logbookPage)
				.append("detail", this.detail).toString();
	}

}