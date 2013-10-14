package com.logbookmanager.data.repository.hibernate;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.validation.constraints.NotNull;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.Assert;

import com.logbookmanager.data.repository.CountryRepository;
import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.Country;
import com.logbookmanager.support.CollationStrength;

public class CountryRepositoryImpl extends HibernateRepository<Country, Long> implements CountryRepository, Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	private static final long serialVersionUID = 912839123L;

	public CountryRepositoryImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	@Cacheable(key = "#locale", value = { "countries" })
	public List<Country> getCountries(@NotNull Locale locale) {
		Assert.notNull(locale);
		SortedSet<Country> countries = new TreeSet<Country>();
		for (String isoCountry : Locale.getISOCountries()) {
			Locale countryAsLocale = new Locale("", isoCountry);
			Country country = new Country(countryAsLocale.getCountry(), locale);
			countries.add(country);
		}
		List<Country> countryList = new ArrayList<>(countries);
		Collections.sort(countryList, new CountryComparator());
		return countryList;
	}

	@Override
	@Cacheable(key = "#locale", value = { "country" })
	public Country getCountry(String isoCode, Locale locale) {
		Locale localeCountry = new Locale("", isoCode);
		Country country = new Country(localeCountry.getCountry(), locale);

//		 check if there is a database record for this country and if there is,
//		 then return that Country Entity
		 Country check = findByNaturalId(country);
		 if (check != null) {
		 country = check;
		 }

		return country;
	}

	@Override
	public Country getCountry(String isoCode) {
		return getCountry(isoCode, Locale.getDefault());
	}

	private class CountryComparator implements Comparator<Country> {

		protected final Logger log = LoggerFactory.getLogger(getClass());

		@Override
		public int compare(Country oThis, Country oThat) {
			// final int AFTER = 1;
			// final int BEFORE = -1;
			final int EQUAL = 0;
			int comparison;

			// this optimisation is usually worthwhile, and can always be added
			if (oThis == oThat) {
				comparison = EQUAL;
			} else {
				// Use collator to compare the country display name
				Collator collator = Collator.getInstance(Locale.getDefault());
				collator.setStrength(CollationStrength.Secondary.getStrength());
				comparison = collator.compare(oThis.getDisplayName(), oThat.getDisplayName());
				if (comparison == 0) {
					log.debug("Collator sees them as the same : " + comparison + ";" + oThis.getDisplayName() + ", "
							+ oThat.getDisplayName() + " - " + CollationStrength.Secondary);
				} else {
					log.debug("Collator sees them as DIFFERENT  : " + comparison + ";" + oThis.getDisplayName() + ", "
							+ oThat.getDisplayName() + " - " + CollationStrength.Secondary);
				}
			}
			return comparison;
		}

	}

}
