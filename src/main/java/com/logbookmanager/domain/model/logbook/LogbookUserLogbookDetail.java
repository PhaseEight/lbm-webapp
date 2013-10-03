package com.logbookmanager.domain.model.logbook;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.logbookmanager.domain.support.EntitySupport;

/**
 * LogbookUserLogbookDetail 
 */
public class LogbookUserLogbookDetail extends
		EntitySupport<LogbookUserLogbookDetail, Long> implements
		java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	// Constructors

	/** default constructor required by Hibernate */
	LogbookUserLogbookDetail() {
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof LogbookUserLogbookDetail)) {
			return false;
		}
		LogbookUserLogbookDetail rhs = (LogbookUserLogbookDetail) object;
		return new EqualsBuilder().append(this.id, rhs.id).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(903933439, 2035327789).append(this.id)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).toString();
	}

}