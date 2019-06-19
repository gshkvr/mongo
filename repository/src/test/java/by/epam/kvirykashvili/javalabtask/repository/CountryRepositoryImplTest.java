package by.epam.kvirykashvili.javalabtask.repository;

import by.epam.kvirykashvili.javalabtask.config.AppConfig;
import by.epam.kvirykashvili.javalabtask.domain.model.Country;
import by.epam.kvirykashvili.javalabtask.domain.parameters.CountrySearchParameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
public class CountryRepositoryImplTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    @Transactional
    public void createCountryTest() {
        Country country = Country.builder()
                .name("Poland")
                .build();
        long countBeforeCreate = countryRepository.readAll().size();
        countryRepository.create(country);
        long countAfterCreate = countryRepository.readAll().size();
        assertEquals(1, countAfterCreate - countBeforeCreate);
    }

    @Test
    @Transactional
    public void readAllCountriesTest() {
        long countriesNamed = countryRepository.readAll().size();
        long countriesCriteria = countryRepository.readList(CountrySearchParameters.builder()
                .build())
                .size();
        assertEquals(countriesNamed, countriesCriteria);
    }

    @Test
    @Transactional
    public void readCountryTest() {
        Country country = countryRepository.readList(CountrySearchParameters.builder()
                .id(2)
                .build())
                .get(0);
        assertEquals("Albania", country.getName());
    }

    @Test
    @Transactional
    public void updateCountryTest() {
        Country country = Country.builder()
                .id(10)
                .name("Estonia")
                .build();
        countryRepository.update(country);
        Country updated = countryRepository.readList(CountrySearchParameters.builder()
                .id(country.getId())
                .build())
                .get(0);
        assertEquals(country.getName(), updated.getName());
    }

    @Test
    @Transactional
    public void deleteCountryTest() {
        Country country = Country.builder()
                .id(26)
                .name("Poland")
                .build();
        long countBeforeDelete = countryRepository.readAll().size();
        countryRepository.delete(country);
        long countAfterDelete = countryRepository.readAll().size();
        assertEquals(1, countBeforeDelete - countAfterDelete);
    }
}