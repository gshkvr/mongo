package by.epam.kvirykashvili.javalabtask.service.mocktests;

import by.epam.kvirykashvili.javalabtask.domain.model.Features;
import by.epam.kvirykashvili.javalabtask.domain.model.Hotel;
import by.epam.kvirykashvili.javalabtask.domain.parameters.HotelSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.HotelService;
import by.epam.kvirykashvili.javalabtask.service.config.ServiceConfig;
import by.epam.kvirykashvili.javalabtask.service.impl.HotelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@ActiveProfiles("test")
public class HotelServiceImplTest {

    private HotelService hotelServiceMock;
    private List<Hotel> hotels;
    private Hotel hotel;

    @Before
    public void init() {
        hotelServiceMock = mock(HotelServiceImpl.class);
        hotel = new Hotel();
        hotel.setName("Hilton");
        hotel.setStars(4);
        hotel.setWebsite("hilton.com");
        hotel.setLatitude("45.23765");
        hotel.setLongitude("48.58233");
        hotel.setFeatures(new Features[]{Features.BALCONY, Features.WIFI, Features.BREAKFAST});
        Hotel hotel1 = new Hotel();
        hotel1.setId(1);
        hotel1.setName("Radisson");
        hotel1.setStars(5);
        hotel1.setWebsite("radisson.com");
        hotel1.setLatitude("44.23423");
        hotel1.setLongitude("42.39425");
        hotel1.setFeatures(new Features[]{Features.BALCONY, Features.WIFI, Features.BREAKFAST});
        Hotel hotel2 = new Hotel();
        hotel2.setId(1);
        hotel2.setName("Warwick");
        hotel2.setStars(1);
        hotel2.setWebsite("warwick.com");
        hotel2.setLatitude("-17.38283");
        hotel2.setLongitude("-128.07254");
        hotel2.setFeatures(new Features[]{Features.DRYER, Features.BREAKFAST});
        Hotel hotel3 = new Hotel();
        hotel3.setId(101);
        hotel3.setName("Minsk");
        hotel3.setStars(1);
        hotel3.setWebsite("minsk.by");
        hotel3.setLatitude("-17.38283");
        hotel3.setLongitude("-128.07254");
        hotel3.setFeatures(new Features[]{Features.DRYER, Features.BREAKFAST});
        hotels = Arrays.asList(hotel1, hotel2, hotel3);
    }

    @Test
    public void createHotelMockTest() {
        hotelServiceMock.create(hotel);
        verify(hotelServiceMock).create(hotel);
    }

    @Test
    public void deleteHotelMockTest() {
        hotelServiceMock.delete(hotel);
        verify(hotelServiceMock).delete(hotel);
    }

    @Test
    public void updateHotelMockTest() {
        hotelServiceMock.update(hotel);
        verify(hotelServiceMock).update(hotel);
    }

    @Test
    public void readAllHotelsMockTest() {
        when(hotelServiceMock.readAll()).thenReturn(hotels);
        List<Hotel> hotels = hotelServiceMock.readAll();
        assertEquals(3, hotels.size());
    }

    @Test
    public void readHotelMockTest() {
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(hotel);
        when(hotelServiceMock.readList(any())).thenReturn(hotelList);
        Hotel h = hotelServiceMock.readList(HotelSearchParameters.builder().id(1).build()).get(0);
        assertEquals("Hilton", h.getName());
    }
}