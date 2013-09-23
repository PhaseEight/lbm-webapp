package com.logbookmanager.data.repository;

import java.util.List;

import com.logbookmanager.domain.model.Country;

/**
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 */
public interface CountryRepository extends Repository<Country, Long> {
	
	/**
	 *  
	 * @return list of Country objects
	 */
	public List<Country> getISOCountries();
	
	public Country getCountry(String isoCode);

}
