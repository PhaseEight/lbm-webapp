package com.logbookmanager.domain.model.logbook;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.logbookmanager.domain.support.EntitySupport;

/**
 */
public class EntryAttribute extends EntitySupport<EntryAttribute, Long>
		implements java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	// Fields
	private DetailAttribute detailAttribute;

	private LogbookUserLogbookEntry logbookUserLogbookEntry;

	private String displayValue;

	private Double sortValue;

	// Constructors

	/** default constructor required by Hibernate */
	EntryAttribute() {
	}

	/**
	 * 
	 */
	public DetailAttribute getDetailAttribute() {
		return this.detailAttribute;
	}

	public void setDetailAttribute(DetailAttribute detailAttribute) {
		this.detailAttribute = detailAttribute;
	}

	/**
	 * 
	 */
	public LogbookUserLogbookEntry getLogbookUserLogbookEntry() {
		return this.logbookUserLogbookEntry;
	}

	public void setLogbookUserLogbookEntry(LogbookUserLogbookEntry logbookUserLogbookEntry) {
		this.logbookUserLogbookEntry = logbookUserLogbookEntry;
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
	public Double getSortValue() {
		return this.sortValue;
	}

	public void setSortValue(Double sortValue) {
		this.sortValue = sortValue;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof EntryAttribute)) {
			return false;
		}
		EntryAttribute rhs = (EntryAttribute) object;
		return new EqualsBuilder().append(this.sortValue, rhs.sortValue)
				.append(this.displayValue, rhs.displayValue)
				.append(this.detailAttribute, rhs.detailAttribute)
				.append(this.id, rhs.id).append(this.version, rhs.version)
				.append(this.logbookUserLogbookEntry, rhs.logbookUserLogbookEntry).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-621737775, 1747928353)
				.append(this.sortValue).append(this.displayValue)
				.append(this.detailAttribute).append(this.id)
				.append(this.version).append(this.logbookUserLogbookEntry).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("detailAttribute", this.detailAttribute)
				.append("displayValue", this.displayValue)
				.append("id", this.id).append("version", this.version)
				.append("sortValue", this.sortValue)
				.append("logbookUserLogbookEntry", this.logbookUserLogbookEntry).toString();
	}

}