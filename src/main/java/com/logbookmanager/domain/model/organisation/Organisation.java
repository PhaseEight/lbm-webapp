package com.logbookmanager.domain.model.organisation;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.NaturalId;

import com.logbookmanager.domain.model.Country;
import com.logbookmanager.domain.model.organisation.support.OrganisationDetails;
import com.logbookmanager.domain.support.EntitySupport;
import com.logbookmanager.domain.support.Title;

/**
 * Organisation
 */
public class Organisation extends EntitySupport<Organisation, Long> implements java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	@NaturalId
	private Title title;

	private OrganisationDetails details;

	private Set<Certification> certifications = new HashSet<Certification>();
	private Set<OrganisationCountry> organisationCountries = new HashSet<OrganisationCountry>();

	/** default constructor */
	Organisation() {
	}

	public Organisation(Title title, OrganisationDetails details) {
		this.setTitle(title);
		this.setDetails(details);
	}

	/**
	 * 
	 */
	public Set<Certification> getCertifications() {
		return this.certifications;
	}

	public void setCertifications(Set<Certification> certifications) {
		this.certifications = certifications;
	}

	public void addCountry(Country country) {
		OrganisationCountry organisationCountry = new OrganisationCountry();
		organisationCountry.setCountry(country);
		organisationCountry.setOrganisation(this);
		if (!organisationCountries.contains(organisationCountry)) {
			organisationCountries.add(organisationCountry);
		}
	}

	/**
	 * 
	 */
	public Set<OrganisationCountry> getOrganisationCountries() {
		return this.organisationCountries;
	}

	public void setOrganisationCountries(Set<OrganisationCountry> organisationCountries) {
		this.organisationCountries = organisationCountries;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Organisation)) {
			return false;
		}
		Organisation rhs = (Organisation) object;

		return new EqualsBuilder().append(this.version, rhs.version).isEquals() && this.sameIdentityAs(rhs);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1137867691, 280120237).append(this.id).append(this.version).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id)
				.append("version", this.version).toString();
	}

	public OrganisationDetails getDetails() {
		return details;
	}

	public void setDetails(OrganisationDetails details) {
		this.details = details;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

}