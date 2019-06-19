package by.epam.kvirykashvili.javalabtask.service.integrationtests;

import by.epam.kvirykashvili.javalabtask.domain.model.Country;
import by.epam.kvirykashvili.javalabtask.domain.parameters.CountrySearchParameters;
import by.epam.kvirykashvili.javalabtask.service.CountryService;
import by.epam.kvirykashvili.javalabtask.service.config.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@ActiveProfiles("test")
public class CountryServiceImplTest {

    @Autowired
    CountryService countryService;

    @Test
    public void createCountryTest() {
        Country country = Country.builder()
                .name("Brazil")
                .build();
        long countBeforeCreate = countryService.readAll().size();
        countryService.create(country);
        long countAfterCreate = countryService.readAll().size();
        assertEquals(1, countAfterCreate - countBeforeCreate);
    }

    @Test
    public void readAllCountriesTest() {
        long countriesNamed = countryService.readAll().size();
        long countriesCriteria = countryService.readList(CountrySearchParameters.builder()
                .build())
                .size();
        assertEquals(countriesNamed, countriesCriteria);
    }

    @Test
    public void readCountryTestTrue() {
        Country country = countryService.readList(CountrySearchParameters.builder()
                .id(2)
                .build())
                .get(0);
        assertEquals("Albania", country.getName());
    }

    @Test
    public void readCountryTestFalse() {
        Country country = countryService.readList(CountrySearchParameters.builder()
                .id(2)
                .build())
                .get(0);
        assertNotEquals("Belarus", country.getName());
    }

    @Test
    public void updateCountryTest() {
        Country country = Country.builder()
                .id(20)
                .name("Monaco")
                .build();
        countryService.update(country);
        Country updated = countryService.readList(CountrySearchParameters.builder()
                .id(country.getId())
                .build())
                .get(0);
        assertEquals(country.getName(), updated.getName());
    }

    @Test
    public void deleteCountryTestTrue() {
        Country country = Country.builder()
                .id(27)
                .build();
        long countBeforeDelete = countryService.readAll().size();
        countryService.delete(country);
        long countAfterDelete = countryService.readAll().size();
        assertEquals(1, countBeforeDelete - countAfterDelete);
    }

    @Test
    public void deleteCountryTestFalse() {
        Country country = Country.builder()
                .id(27)
                .build();
        long countBeforeDelete = countryService.readAll().size();
        countryService.delete(country);
        long countAfterDelete = countryService.readAll().size();
        assertNotEquals(1, countBeforeDelete - countAfterDelete);
    }
}