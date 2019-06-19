package by.epam.kvirykashvili.javalabtask.repository;

import by.epam.kvirykashvili.javalabtask.config.AppConfig;
import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.model.TourType;
import by.epam.kvirykashvili.javalabtask.domain.parameters.CountrySearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.HotelSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.TourSearchParameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
public class TourRepositoryImplTest {

    @Autowired
    TourRepository tourRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    CountryRepository countryRepository;

    @Test
    @Transactional
    public void createTourTest() {
        Tour tour = Tour.builder()
                .photo("//photos/1629991393799")
                .date(new Date())
                .duration(14)
                .description("Two weeks tour to Qatar.")
                .cost(1400)
                .tourType(TourType.SEASONAL)
                .hotel(hotelRepository.readList(HotelSearchParameters.builder()
                        .id(50)
                        .build())
                        .get(0))
                .country(countryRepository.readList(CountrySearchParameters.builder()
                        .id(5)
                        .build())
                        .get(0))
                .build();
        long countBeforeCreate = tourRepository.readAll().size();
        tourRepository.create(tour);
        long countAfterCreate = tourRepository.readAll().size();
        assertEquals(1, countAfterCreate - countBeforeCreate);
    }

    @Test
    @Transactional
    public void readAllToursTest() {
        long toursNamed = tourRepository.readAll().size();
        long toursCriteria = tourRepository.readList(TourSearchParameters.builder()
                .build())
                .size();
        assertEquals(toursNamed, toursCriteria);
    }

    @Test
    @Transactional
    public void readTourTest() {
        Tour tour = tourRepository.readList(TourSearchParameters.builder()
                .id(1)
                .build())
                .get(0);
        assertEquals("Perfect culinary tour", tour.getDescription());
    }

    @Test
    @Transactional
    public void updateTourTest() {
        Tour tour = Tour.builder()
                .id(20)
                .photo("//photos/1395741393799")
                .date(new Date())
                .duration(9)
                .description("Two weeks tour to Egypt.")
                .cost(1400)
                .tourType(TourType.SEASONAL)
                .hotel(hotelRepository.readList(HotelSearchParameters.builder()
                        .id(43)
                        .build())
                        .get(0))
                .country(countryRepository.readList(CountrySearchParameters.builder()
                        .id(8)
                        .build())
                        .get(0))
                .build();
        tourRepository.update(tour);
        Tour updated = tourRepository.readList(TourSearchParameters.builder()
                .id(tour.getId())
                .build())
                .get(0);
        assertEquals(tour.getDescription(), updated.getDescription());
    }

    @Test
    @Transactional
    public void deleteTourTest() {
        Tour tour = Tour.builder()
                .id(1001)
                .build();
        long countBeforeDelete = tourRepository.readAll().size();
        tourRepository.delete(tour);
        long countAfterDelete = tourRepository.readAll().size();
        assertEquals(1, countBeforeDelete - countAfterDelete);
    }
}