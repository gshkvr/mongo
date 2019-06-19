package by.epam.kvirykashvili.javalabtask.service.mocktests;

import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.model.TourType;
import by.epam.kvirykashvili.javalabtask.domain.parameters.TourSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.TourService;
import by.epam.kvirykashvili.javalabtask.service.config.ServiceConfig;
import by.epam.kvirykashvili.javalabtask.service.impl.TourServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@ActiveProfiles("test")
public class TourServiceImplTest {

    private TourService tourServiceMock;
    private List<Tour> tours;
    private Tour tour;

    @Before
    public void init() {
        tourServiceMock = mock(TourServiceImpl.class);
        tour = new Tour();
        tour.setPhoto("//photos/1620041393799");
        tour.setDate(new Date());
        tour.setDuration(14);
        tour.setDescription("Two weeks tour to Qatar.");
        tour.setCost(1400);
        tour.setTourType(TourType.SEASONAL);
        Tour tour1 = new Tour();
        tour1.setId(1000);
        tour1.setPhoto("//photos/1606081853999");
        tour1.setDate(new Date());
        tour1.setDuration(9);
        tour1.setDescription("Nice adventure tour");
        tour1.setCost(1264);
        tour1.setTourType(TourType.ADVENTURE);
        Tour tour2 = new Tour();
        tour2.setId(1000);
        tour2.setPhoto("//photos/1606081853999");
        tour2.setDate(new Date());
        tour2.setDuration(9);
        tour2.setDescription("Nice family tour");
        tour2.setCost(1264);
        tour2.setTourType(TourType.FAMILY);
        tours = Arrays.asList(tour1, tour2);
    }

    @Test
    public void createTourMockTest() {
        tourServiceMock.create(tour);
        verify(tourServiceMock).create(tour);
    }

    @Test
    public void deleteTourMockTest() {
        tourServiceMock.delete(tour);
        verify(tourServiceMock).delete(tour);
    }

    @Test
    public void updateTourMockTest() {
        tourServiceMock.update(tour);
        verify(tourServiceMock).update(tour);
    }

    @Test
    public void readAllToursMockTest() {
        when(tourServiceMock.readAll()).thenReturn(tours);
        List<Tour> countryList = tourServiceMock.readAll();
        assertEquals(2, countryList.size());
    }

    @Test
    public void readTourMockTest() {
        List<Tour> tourList = new ArrayList<>();
        tourList.add(tour);
        when(tourServiceMock.readList(any())).thenReturn(tourList);
        Tour t = tourServiceMock.readList(TourSearchParameters.builder().id(1).build()).get(0);
        assertEquals("Two weeks tour to Qatar.", t.getDescription());
    }
}