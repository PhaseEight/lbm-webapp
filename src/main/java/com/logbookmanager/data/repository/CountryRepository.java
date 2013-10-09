package com.logbookmanager.data.repository;

import java.util.List;
import java.util.Locale;

import com.logbookmanager.domain.model.Country;

/**
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 */
public interface CountryRepository {

	/**
	 * 
	 * @return list of Country objects
	 */
	public List<Country> getCountries(Locale locale);

	public Country getCountry(String isoCode, Locale locale);

	public Country getCountry(String isoCode);

}
