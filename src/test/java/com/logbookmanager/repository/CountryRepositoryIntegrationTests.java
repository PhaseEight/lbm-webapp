package com.logbookmanager.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.transaction.annotation.Transactional;

import com.logbookmanager.data.repository.CountryRepository;
import com.logbookmanager.data.repository.CountryRepositoryImpl;
import com.logbookmanager.domain.model.Country;

public class CountryRepositoryIntegrationTests {
	
	@Inject
	@Mock
	CountryRepository countryRepository = new CountryRepositoryImpl();

	@Test
	public void getISOCountries(){
		List<Country> countries = countryRepository.getISOCountries();
		assertEquals("there aren't enough countries: ", 249, countries.size());
		for(Country country: countries){
			System.out.println(country.toString());
		}
		CountryRepository mockrepo = mock(CountryRepositoryImpl.class); 
		when(mockrepo.findAll()).thenReturn(countries);
		assertTrue("there weren't enough countries found in Mock",mockrepo.findAll().size() == 249);
	}
	
	@Test
	@Transactional
	public void saveCountry() {
		countryRepository.getCountry("GB");
	}
	
}
