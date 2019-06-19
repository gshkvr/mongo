package by.epam.kvirykashvili.javalabtask.service.integrationtests;

import by.epam.kvirykashvili.javalabtask.domain.model.Features;
import by.epam.kvirykashvili.javalabtask.domain.model.Hotel;
import by.epam.kvirykashvili.javalabtask.domain.parameters.HotelSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.HotelService;
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
public class HotelServiceImplTest {

    @Autowired
    HotelService hotelService;

    @Test
    public void createHotelTest() {
        Hotel hotel = Hotel.builder()
                .name("Hilton")
                .stars(4)
                .website("hilton.com")
                .latitude("45.23765")
                .longitude("48.58233")
                .features(new Features[]{Features.BALCONY, Features.WIFI, Features.BREAKFAST})
                .build();
        long countBeforeCreate = hotelService.readAll().size();
        hotelService.create(hotel);
        long countAfterCreate = hotelService.readAll().size();
        assertEquals(1, countAfterCreate - countBeforeCreate);
    }

    @Test
    public void readAllHotelsTest() {
        long hotelsNative = hotelService.readAll().size();
        long hotelCriteria = hotelService.readList(HotelSearchParameters.builder()
                .build())
                .size();
        assertEquals(hotelsNative, hotelCriteria);
    }

    @Test
    public void readHotelTestTrue() {
        Hotel hotel = hotelService.readList(HotelSearchParameters.builder()
                .id(25)
                .build())
                .get(0);
        assertEquals("Genappe", hotel.getName());
    }

    @Test
    public void readHotelTestFalse() {
        Hotel hotel = hotelService.readList(HotelSearchParameters.builder()
                .id(25)
                .build())
                .get(0);
        assertNotEquals("Warwick", hotel.getName());
    }

    @Test
    public void deleteHotelTestTrue() {
        Hotel hotel = Hotel.builder()
                .id(101)
                .build();
        long countBeforeDelete = hotelService.readAll().size();
        hotelService.delete(hotel);
        long countAfterDelete = hotelService.readAll().size();
        assertEquals(1, countBeforeDelete - countAfterDelete);
    }

    @Test
    public void deleteHotelTestFalse() {
        Hotel hotel = Hotel.builder()
                .id(105)
                .build();
        long countBeforeDelete = hotelService.readAll().size();
        hotelService.delete(hotel);
        long countAfterDelete = hotelService.readAll().size();
        assertNotEquals(1, countBeforeDelete - countAfterDelete);
    }
}