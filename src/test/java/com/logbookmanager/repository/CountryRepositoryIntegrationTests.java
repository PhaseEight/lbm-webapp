package com.logbookmanager.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.logbookmanager.data.repository.CountryRepository;
import com.logbookmanager.data.repository.CountryRepositoryImpl;
import com.logbookmanager.domain.model.Country;
import com.logbookmanager.support.IntegrationTestSupport;

public class CountryRepositoryIntegrationTests extends IntegrationTestSupport {

	@Inject
	@Mock
	CountryRepository countryRepository = new CountryRepositoryImpl();

	@Test
	public void getISOCountries() {
		List<Country> countries = countryRepository.getISOCountries();
		assertEquals("there aren't enough countries: ", 249, countries.size());
		for (Country country : countries) {
			System.out.println(country.toString());
		}
		CountryRepository mockrepo = mock(CountryRepositoryImpl.class);
		when(mockrepo.findAll()).thenReturn(countries);
		assertTrue("there weren't enough countries found in Mock", mockrepo.findAll().size() == 249);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void saveCountry() {
		Country country = countryRepository.getCountry(Locale.FRANCE.getCountry());
		saveCountry(country);
		
		
		List<Country> countries = jdbcTemplate.query("select ID, VERSION, ANSI_CODE, DISPLAY_NAME from COUNTRY where ANSI_CODE='FR'",
				new RowMapper<Country>() {
					@Override
					public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Country(rs.getString(3), rs.getString(4));
					}
				});

		assertTrue("there must be only 1 country found; found: " + countries.size(), countries.size() == 1);

		assertEquals("there must be a country with the Ansi Code FR", "FR", countries.get(0).getAnsiCode());

	}

	private void saveCountry(Country country) {
		countryRepository.makePersistent(country);
		getSessionFactory().getCurrentSession().flush();
	}

}
