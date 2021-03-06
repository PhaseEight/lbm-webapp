package com.logbookmanager.domain.model.organisation;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.logbookmanager.domain.support.EntitySupport;

/**
 * Certification
 */
public class Certification extends EntitySupport<Certification, Long> implements java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	// Fields
	private Organisation organisation;

	private String name;

	private String commonName;

	private Byte displayCommon;

	// Constructors

	/** default constructor */
	Certification() {
	}

	/**
	 * 
	 */
	public Organisation getOrganisation() {
		return this.organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
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
	public String getCommonName() {
		return this.commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	/**
	 * 
	 */
	public Byte getDisplayCommon() {
		return this.displayCommon;
	}

	public void setDisplayCommon(Byte displayCommon) {
		this.displayCommon = displayCommon;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Certification)) {
			return false;
		}
		Certification rhs = (Certification) object;
		return new EqualsBuilder().append(this.displayCommon, rhs.displayCommon)
				.append(this.commonName, rhs.commonName).append(this.name, rhs.name)
				.append(this.organisation, rhs.organisation).append(this.id, rhs.id).append(this.version, rhs.version)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1500570745, -319488509).append(this.displayCommon).append(this.commonName)
				.append(this.name).append(this.organisation).append(this.id).append(this.version).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name).append("displayCommon", this.displayCommon)
				.append("organisation", this.organisation).append("id", this.id).append("version", this.version)
				.append("commonName", this.commonName).toString();
	}

}