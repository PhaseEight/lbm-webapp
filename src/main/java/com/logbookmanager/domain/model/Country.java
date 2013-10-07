package com.logbookmanager.domain.model;

/**
 * protected Logger log;
 * this.log = LoggerFactory.getLogger(getClass());
 */
import java.text.Collator;
import java.util.Locale;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.NaturalId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.logbookmanager.domain.support.EntitySupport;
import com.logbookmanager.support.CollationStrength;

/**
 * This class does not represent Locale. Use the Locale class and
 * LocaleContextHolder for Application specific Local sensitive operations.
 * 
 * @see com.logbookmanager.domain.model.organisation.OrganisationCountries
 * 
 */
public class Country extends EntitySupport<Country, Long> implements java.io.Serializable, Comparable<Country> {

	private static final long serialVersionUID = 912839123L;
	
	private static final Logger log = LoggerFactory.getLogger(Country.class.getName());

	@NaturalId
	private String ansiCode;
	private Locale locale;
	
	@Transient
	private String displayName;


	/** default constructor required by Hibernate */
	/* required by Hibernate */
	Country() {
	}

	/**
	 * 
	 * @param ansiCode this is the 2 or 3 letter iso code for the country
	 * @param displayName this is the 
	 * @param locale
	 */
	public Country(@NotNull String ansiCode, @NotNull Locale locale) {
		Assert.notNull(ansiCode);
		Assert.notNull(locale);
		this.ansiCode = ansiCode;
		this.displayName = (new Locale("",ansiCode).getDisplayCountry(locale));
		this.locale = locale;
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

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
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
		return new EqualsBuilder().append(this.ansiCode, rhs.ansiCode).append(this.displayName, rhs.displayName).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-722875075, 618856429).append(this.ansiCode).append(this.displayName).toHashCode();
	}

	/**
	 * This is overridden to make sure that country is sorted by Display Name
	 * This carries out a collated comparison
	 */
	@Override
	public int compareTo(Country other) {
		
//		final int AFTER = 1;
//		final int BEFORE = -1;
		final int EQUAL = 0;
		int comparison;

		// this optimisation is usually worthwhile, and can always be added
		if (this == other) {
			comparison = EQUAL;
		} else {
			// Use collator to compare the country display name
			Collator collator = Collator.getInstance(Locale.getDefault());
		    collator.setStrength(CollationStrength.Secondary.getStrength());
		    comparison = collator.compare(this.getDisplayName(), other.getDisplayName());
		    if ( comparison == 0 ) {
		      log.debug("Collator sees them as the same : " + this.getDisplayName() + ", " + other.getDisplayName() + " - " + CollationStrength.Identical);
		    }
		    else {
		    	log.debug("Collator sees them as DIFFERENT  : " + this.getDisplayName() + ", " + other.getDisplayName() + " - " + CollationStrength.Identical);
		    }
		}
		return comparison;
	}

}