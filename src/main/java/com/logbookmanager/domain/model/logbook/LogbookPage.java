package com.logbookmanager.domain.model.logbook;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.logbookmanager.domain.support.EntitySupport;

/**
 * =LogbookPage
 * 
 * ==
 * 
 */
public class LogbookPage extends EntitySupport<LogbookPage, Long> implements
		java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	// Fields
	private Logbook logbook;

	private String name;

	private Integer displayOrder;

	private Set<LogbookPageDetail> logbookPageDetails = new HashSet<LogbookPageDetail>();

	// Constructors

	/** default constructor required by Hibernate */
	LogbookPage() {
	}

	/**
	 * 
	 */
	public Logbook getLogbook() {
		return this.logbook;
	}

	public void setLogbook(Logbook logbook) {
		this.logbook = logbook;
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
	public Integer getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
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
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof LogbookPage)) {
			return false;
		}
		LogbookPage rhs = (LogbookPage) object;
		return new EqualsBuilder().append(this.displayOrder, rhs.displayOrder)
				.append(this.logbook, rhs.logbook)
				.append(this.logbookPageDetails, rhs.logbookPageDetails)
				.append(this.name, rhs.name).append(this.id, rhs.id)
				.append(this.version, rhs.version).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1573721405, 1508961587)
				.append(this.displayOrder).append(this.logbook)
				.append(this.logbookPageDetails).append(this.name)
				.append(this.id).append(this.version).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name)
				.append("id", this.id).append("version", this.version)
				.append("displayOrder", this.displayOrder)
				.append("logbook", this.logbook)
				.append("logbookPageDetails", this.logbookPageDetails)
				.toString();
	}

}