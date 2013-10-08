package com.logbookmanager.domain.model.organisation;

import com.logbookmanager.domain.model.Country;

/**
 */
public class OrganisationCountryId implements java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	// Fields

	private Organisation organisation;

	private Country country;

	/** default constructor */
	public OrganisationCountryId() {
		// default empty constructor
	}

	public OrganisationCountryId(Organisation org, Country country) {
		this.organisation = org;
		this.country = country;
	}

	// Property accessors

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
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrganisationCountryId))
			return false;
		OrganisationCountryId castOther = (OrganisationCountryId) other;

		return (this.getOrganisation() == castOther.getOrganisation())
				|| (this.getOrganisation() != null && castOther.getOrganisation() != null && this.getOrganisation()
						.equals(castOther.getOrganisation()))
				&& (this.getCountry() == castOther.getCountry())
				|| (this.getCountry() != null && castOther.getCountry() != null && this.getCountry().equals(
						castOther.getCountry()));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + ((country == null) ? 3 : country.hashCode());
		result = 37 * result + ((organisation == null) ? 5 : organisation.hashCode());
		return result;
	}

}