package by.epam.kvirykashvili.javalabtask.service.integrationtests;

import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.model.TourType;
import by.epam.kvirykashvili.javalabtask.domain.parameters.HotelSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.TourSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.CountryService;
import by.epam.kvirykashvili.javalabtask.service.HotelService;
import by.epam.kvirykashvili.javalabtask.service.TourService;
import by.epam.kvirykashvili.javalabtask.service.config.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@ActiveProfiles("test")
public class TourServiceImplTest {

    @Autowired
    TourService tourService;

    @Autowired
    HotelService hotelService;

    @Autowired
    CountryService countryService;

    @Test
    public void createTourTest() {
        Tour tour = Tour.builder()
                .photo("//photos/1620041393799")
                .date(new Date())
                .duration(14)
                .description("Two weeks tour to Qatar.")
                .cost(1400)
                .tourType(TourType.SEASONAL)
                .hotel(hotelService.readList(HotelSearchParameters.builder()
                        .id(50)
                        .build())
                        .get(0))
                .country(countryService.readList(HotelSearchParameters.builder()
                        .id(5)
                        .build())
                        .get(0))
                .build();
        long countBeforeCreate = tourService.readAll().size();
        tourService.create(tour);
        long countAfterCreate = tourService.readAll().size();
        assertEquals(1, countAfterCreate - countBeforeCreate);
    }

    @Test
    public void readAllToursTest() {
        long toursNamed = tourService.readAll().size();
        long toursCriteria = tourService.readList(TourSearchParameters.builder()
                .build())
                .size();
        assertEquals(toursNamed, toursCriteria);
    }

    @Test
    public void readTourTestTrue() {
        Tour tour = tourService.readList(TourSearchParameters.builder()
                .id(1)
                .build())
                .get(0);
        assertEquals("Perfect culinary tour", tour.getDescription());
    }

    @Test
    public void readTourTestFalse() {
        Tour tour = tourService.readList(TourSearchParameters.builder()
                .id(2)
                .build())
                .get(0);
        assertNotEquals("Perfect culinary tour", tour.getDescription());
    }

    @Test
    public void updateTourTest() {
        Tour tour = Tour.builder()
                .id(899)
                .photo("//photos/1606081853999")
                .date(new Date())
                .duration(9)
                .description("Nice adventure tour")
                .cost(1264)
                .tourType(TourType.SEASONAL)
                .hotel(hotelService.readList(HotelSearchParameters.builder()
                        .id(63)
                        .build())
                        .get(0))
                .country(countryService.readList(HotelSearchParameters.builder()
                        .id(16)
                        .build())
                        .get(0))
                .build();
        tourService.update(tour);
        Tour updated = tourService.readList(TourSearchParameters.builder()
                .id(tour.getId())
                .build())
                .get(0);
        assertEquals(tour.getDescription(), updated.getDescription());
    }

    @Test
    public void deleteTourTestTrue() {
        Tour tour = Tour.builder()
                .id(700)
                .build();
        long countBeforeDelete = tourService.readAll().size();
        tourService.delete(tour);
        long countAfterDelete = tourService.readAll().size();
        assertEquals(1, countBeforeDelete - countAfterDelete);
    }

    @Test
    public void deleteTourTestFalse() {
        Tour tour = Tour.builder()
                .id(1007)
                .build();
        long countBeforeDelete = tourService.readAll().size();
        tourService.delete(tour);
        long countAfterDelete = tourService.readAll().size();
        assertNotEquals(1, countBeforeDelete - countAfterDelete);
    }
}