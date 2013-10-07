package com.logbookmanager.data.repository;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.Country;

@Repository
public class CountryRepositoryImpl extends HibernateRepository<Country, Long> implements CountryRepository, Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	private static final long serialVersionUID = 912839123L;

	private final Map<String, Country> countryMap = new ConcurrentHashMap<String, Country>(249);
	private final Set<Country> countries = new ConcurrentSkipListSet<Country>();

	public CountryRepositoryImpl() {
		for (String isoCountry : Locale.getISOCountries()) {
			Locale locale = new Locale("", isoCountry);
			Country country = new Country(locale.getCountry(), locale.getDisplayCountry());
			countries.add(country);
			countryMap.put(country.getAnsiCode(), country);
		}
	}

	@Override
	@Cacheable(value = { "countries" })
	public Set<Country> getCountries() {
		Set<Country> retCountries = new ConcurrentSkipListSet<>(countries);
		return retCountries;
	}

	@Override
	@Cacheable(value = { "country" })
	public Country getCountry(String isoCode) {
		Locale locale = new Locale("", isoCode);
		Country country = new Country(locale.getCountry(), locale.getDisplayCountry());

		// check if there is a database record for this country and if there is, then return that Country Entity
		try {
			country = findByNaturalId(country);
		} catch (Throwable t) {
			log.info("Country not found in Database using Locale based country");
		}

		return country;
	}
}
