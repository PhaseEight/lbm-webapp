package com.logbookmanager.domain.model.logbook;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.logbookmanager.domain.model.person.PersonLogbookDetail;
import com.logbookmanager.domain.support.EntitySupport;

/**
 */
public class Detail extends EntitySupport<Detail, Long> implements
		java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	// Fields
	private String name;

	private String displayValue;

	private Set<DetailAttribute> detailAttributes = new HashSet<DetailAttribute>();

	// These are details assigned to the logbook by the logbook administrator
	private Set<LogbookPageDetail> logbookPageDetails = new HashSet<LogbookPageDetail>();

	// These are details assigned to the logbook by the logbook user/owner
	private Set<PersonLogbookDetail> userLogbookDetails = new HashSet<PersonLogbookDetail>();

	// Constructors

	// required by Hibernate
	Detail() {
	}

	/**
	 * 
	 */
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public String getDisplayValue() {
		return this.displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	/**
	 * 
	 */
	public Set<DetailAttribute> getDetailAttributes() {
		return this.detailAttributes;
	}

	public void setDetailAttributes(Set<DetailAttribute> detailAttributes) {
		this.detailAttributes = detailAttributes;
	}

	/**
	 * 
	 */
	public Set<LogbookPageDetail> getLogbookPageDetails() {
		return this.logbookPageDetails;
	}

	public void setLogbookPageDetails(Set<LogbookPageDetail> logbookPageDetails) {
		this.logbookPageDetails = logbookPageDetails;
	}

	/**
	 * 
	 */
	public Set<PersonLogbookDetail> getUserLogbookDetails() {
		return this.userLogbookDetails;
	}

	public void setUserLogbookDetails(
			Set<PersonLogbookDetail> userLogbookDetails) {
		this.userLogbookDetails = userLogbookDetails;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Detail)) {
			return false;
		}
		Detail rhs = (Detail) object;
		return new EqualsBuilder().append(this.name, rhs.name)
				.append(this.displayValue, rhs.displayValue)
				.append(this.id, rhs.id).append(this.version, rhs.version)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1353038431, -681572337).append(this.name)
				.append(this.displayValue).append(this.id).append(this.version)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name)
				.append("displayValue", this.displayValue)
				.append("id", this.id).append("version", this.version)
				.toString();
	}

}