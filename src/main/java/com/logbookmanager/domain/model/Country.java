package com.logbookmanager.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.NaturalId;

import com.logbookmanager.domain.support.EntitySupport;

/**
 * This class does not represent Locale. Use the Locale class and
 * LocaleContextHolder for Application specific Local sensitive operations.
 * 
 * @see com.logbookmanager.domain.model.organisation.OrganisationCountries 
 * 
 */
public class Country extends EntitySupport<Country, Long> implements java.io.Serializable {

	private static final long serialVersionUID = 912839123L;

	@NaturalId
	private String ansiCode;
	private String displayName;

	/** default constructor required by Hibernate */
	public Country(String ansiCode, String displayName) {
		this.ansiCode = ansiCode;
		this.displayName = displayName;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("active", this.active)
				.append("lastUpdateTimeStamp", this.lastUpdateTimeStamp).append("ansiCode", this.ansiCode)
				.append("displayName", this.displayName).toString();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Country)) {
			return false;
		}
		Country rhs = (Country) object;
		return new EqualsBuilder().append(this.ansiCode, rhs.ansiCode).append(this.displayName, rhs.displayName)
				.append(this.id, rhs.id).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-722875075, 618856429).append(this.ansiCode).append(this.displayName)
				.append(this.id).toHashCode();
	}

	/* required by Hibernate */
	Country() {
	}

	public String getAnsiCode() {
		return ansiCode;
	}

	public void setAnsiCode(String ansiCode) {
		this.ansiCode = ansiCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}