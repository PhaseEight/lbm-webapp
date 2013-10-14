package com.logbookmanager.data.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.lang3.LocaleUtils;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.logbookmanager.data.repository.CountryRepository;
import com.logbookmanager.data.repository.hibernate.CountryRepositoryImpl;
import com.logbookmanager.domain.model.Country;
import com.logbookmanager.support.IntegrationTestSupport;

public class CountryRepositoryIntegrationTests extends IntegrationTestSupport {

	private static final Logger logger = LoggerFactory.getLogger(CountryRepositoryIntegrationTests.class.getName());

	@Inject
	@Mock
	CountryRepository countryRepository = new CountryRepositoryImpl(super.getSessionFactory());

	@Test
	public void getCountriesAsFrenchList() {
		Locale locale = LocaleUtils.toLocale("fr_FR");
		List<Country> countries = countryRepository.getCountries(locale);
		assertEquals("there aren't enough countries: ", 249, countries.size());
		int i = 1;
		for (Country country : countries) {
			logger.debug(i++ + ": " + country.toString());
		}
		assertTrue("The 2nd country must be ZA, South Afique du Sud",
				countries.get(1).getAnsiCode().equalsIgnoreCase("ZA"));

	}

	@Test
	public void getCountriesAsGermanList() {
		Locale locale = LocaleUtils.toLocale("de_DE");
		List<Country> countries = countryRepository.getCountries(locale);
		assertEquals("there aren't enough countries: ", 249, countries.size());
		int i = 1;
		for (Country country : countries) {
			logger.debug(i++ + ": " + country.toString());
		}
		assertTrue("The 208th country must be ZA, Südafrika", countries.get(207).getAnsiCode().equalsIgnoreCase("ZA"));

	}

	@Test
	public void getCountriesAsJapaneseList() {
		Locale locale = Locale.JAPAN;
		List<Country> countries = countryRepository.getCountries(locale);
		assertEquals("there aren't enough countries: ", 249, countries.size());
		int i = 1;
		for (Country country : countries) {
			logger.debug(i++ + ": " + country.getDisplayName());
		}
		assertTrue("The 208th country must be ZA, Südafrika", countries.get(234).getAnsiCode().equalsIgnoreCase("ZA"));

	}

	@Test
	@Transactional
	@Rollback(true)
	public void saveCountry() {
		Country country = countryRepository.getCountry(Locale.FRANCE.getCountry());
		saveCountry(country);

		List<Country> countries = jdbcTemplate.query("select ID, VERSION, ANSI_CODE from COUNTRY where ANSI_CODE='FR'",
				new RowMapper<Country>() {
					@Override
					public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Country(rs.getString(3), Locale.getDefault());
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
