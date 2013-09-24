package com.logbookmanager.domain.model.person;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.logbookmanager.domain.support.EntitySupport;

/**
 * PersonLogbookDetail generated by hbm2java
 */
public class PersonLogbookDetail extends
		EntitySupport<PersonLogbookDetail, Long> implements
		java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	// Constructors

	/** default constructor required by Hibernate */
	PersonLogbookDetail() {
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PersonLogbookDetail)) {
			return false;
		}
		PersonLogbookDetail rhs = (PersonLogbookDetail) object;
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