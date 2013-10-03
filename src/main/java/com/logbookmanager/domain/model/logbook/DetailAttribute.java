package com.logbookmanager.domain.model.logbook;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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