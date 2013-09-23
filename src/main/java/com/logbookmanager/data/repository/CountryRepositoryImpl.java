package com.logbookmanager.data.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.logbookmanager.data.support.hibernate.HibernateRepository;
import com.logbookmanager.domain.model.Country;

@Repository
public class CountryRepositoryImpl extends HibernateRepository<Country, Long> implements CountryRepository, Serializable {
	
	private static final long serialVersionUID = 912839123L;
	public CountryRepositoryImpl() {
	}
	
	@Override
	@Cacheable(value = { "isoCountries" })
	public List<Country> getISOCountries(){
		List<Country> countries = new ArrayList<Country>();
		for(String isoCountry : Locale.getISOCountries()){
			Locale locale = new Locale("", isoCountry);
			Country country = new Country(locale.getCountry(),locale.getDisplayCountry());
			countries.add(country);
		} 
		return countries;
	}
	
	@Override
	@Cacheable(value = { "country" })
	public Country getCountry(String isoCode){
			Locale locale = new Locale("", isoCode);
			Country country = new Country(locale.getCountry(),locale.getDisplayCountry());
		return country;
	}
	
}
