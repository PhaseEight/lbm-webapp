package com.logbookmanager.domain.model.logbook;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.logbookmanager.domain.support.EntitySupport;

/**
 */
public class DetailAttribute extends EntitySupport<DetailAttribute, Long>
		implements java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	// Fields
	private Detail detail;

	private String name;

	private String commonName;

	// this will actually be, which entries have this attribute of this detail
	private Set<EntryAttribute> entryAttributes = new HashSet<EntryAttribute>();

	// Constructors

	// required by Hibernate
	DetailAttribute() {
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
		if (!(object instanceof DetailAttribute)) {
			return false;
		}
		DetailAttribute rhs = (DetailAttribute) object;
		return new EqualsBuilder().append(this.commonName, rhs.commonName)
				.append(this.detail, rhs.detail).append(this.name, rhs.name)
				.append(this.id, rhs.id).append(this.version, rhs.version)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-43202197, -2047995887)
				.append(this.commonName).append(this.detail).append(this.name)
				.append(this.id).append(this.version).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name)
				.append("id", this.id).append("version", this.version)
				.append("commonName", this.commonName)
				.append("detail", this.detail).toString();
	}

}