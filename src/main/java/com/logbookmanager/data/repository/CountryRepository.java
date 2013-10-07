package com.logbookmanager.data.repository;

import java.util.Set;

import com.logbookmanager.domain.model.Country;

/**
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 */
public interface CountryRepository extends Repository<Country, Long> {
	
	/**
	 *  
	 * @return list of Country objects
	 */
	public Set<Country> getCountries();
	
	public Country getCountry(String isoCode);

}
