package by.epam.kvirykashvili.javalabtask.service.mocktests;

import by.epam.kvirykashvili.javalabtask.domain.model.Country;
import by.epam.kvirykashvili.javalabtask.domain.parameters.CountrySearchParameters;
import by.epam.kvirykashvili.javalabtask.service.CountryService;
import by.epam.kvirykashvili.javalabtask.service.config.ServiceConfig;
import by.epam.kvirykashvili.javalabtask.service.impl.CountryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@ActiveProfiles("test")
public class CountryServiceImplTest {

    private CountryService countryServiceMock;
    private List<Country> countries;
    private Country country;

    @Before
    public void init() {
        countryServiceMock = mock(CountryServiceImpl.class);
        country = new Country();
        country.setId(101);
        country.setName("Japan");
        Country country1 = new Country();
        country1.setId(1);
        country1.setName("Italy");
        Country country2 = new Country();
        country2.setId(2);
        country2.setName("France");
        Country country3 = new Country();
        country3.setId(3);
        country3.setName("Spain");
        countries = Arrays.asList(country1, country2, country3);
    }

    @Test
    public void createCountryMockTest() {
        countryServiceMock.create(country);
        verify(countryServiceMock).create(country);
    }

    @Test
    public void deleteCountryMockTest() {
        countryServiceMock.delete(country);
        verify(countryServiceMock).delete(country);
    }

    @Test
    public void updateCountryMockTest() {
        countryServiceMock.update(country);
        verify(countryServiceMock).update(country);
    }

    @Test
    public void readAllCountriesMockTest() {
        when(countryServiceMock.readAll()).thenReturn(countries);
        List<Country> countryList = countryServiceMock.readAll();
        assertEquals(3, countryList.size());
    }

    @Test
    public void readCountryMockTest() {
        List<Country> countryList = new ArrayList<>();
        countryList.add(country);
        when(countryServiceMock.readList(any())).thenReturn(countryList);
        Country c = countryServiceMock.readList(CountrySearchParameters.builder().id(1).build()).get(0);
        assertEquals("Japan", c.getName());
    }
}