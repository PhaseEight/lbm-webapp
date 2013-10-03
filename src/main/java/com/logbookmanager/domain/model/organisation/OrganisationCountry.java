package com.logbookmanager.domain.model.organisation;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.logbookmanager.domain.model.Country;
import com.logbookmanager.domain.model.support.Address;
import com.logbookmanager.domain.support.EntitySupport;

/**
 * Countries exist Organisations exist
 * 
 * Organisation countries are individual entities due to the corporate
 * complexities of the world and the individuality that exists through all regions
 * 
 * OrganisationCountry
 */
public class OrganisationCountry extends
		EntitySupport<OrganisationCountry, Long> implements
		java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	// TODO: Include Address class for Organisation
	private String phone;
	private String fax;
	
	// Address will contain the same country as the organisation country
	private Address address;
	
	private Country country;
	private Organisation organisation;

	/** default constructor required by Hibernate */
	OrganisationCountry() {
	}

	public OrganisationCountry(Country country, Organisation organisation) {
		this.country = country;
		this.organisation = organisation;
		this.address = new Address(country);
	}

	// Property accessors
	public Organisation getOrganisation() {
		return this.organisation;
	}

	public Country getCountry() {
		return this.country;
	}

	// Property accessors
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * 
	 */
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 
	 */
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof OrganisationCountry)) {
			return false;
		}
		OrganisationCountry rhs = (OrganisationCountry) object;
		return new EqualsBuilder().append(this.phone, rhs.phone)
				.append(this.fax, rhs.fax)
				.append(this.country, rhs.getCountry())
				.append(getVersion(), rhs.getVersion())
				.append(this.organisation, rhs.getOrganisation()).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(100285679, 1786133485).append(this.phone)
				.append(this.fax).append(this.country).append(getVersion())
				.append(this.organisation).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("phone", this.phone)
				.append("organisation", this.organisation)
				.append("country", this.country)
				.append("version", getVersion()).append("fax", this.fax)
				.toString();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}