package by.epam.kvirykashvili.javalabtask.repository;

import by.epam.kvirykashvili.javalabtask.config.AppConfig;
import by.epam.kvirykashvili.javalabtask.domain.model.Features;
import by.epam.kvirykashvili.javalabtask.domain.model.Hotel;
import by.epam.kvirykashvili.javalabtask.domain.parameters.HotelSearchParameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
public class HotelRepositoryImplTest {

    @Autowired
    HotelRepository hotelRepository;

    @Test
    @Transactional
    public void createHotelTest() {
        Hotel hotel = Hotel.builder()
                .name("Hilton")
                .stars(4)
                .website("hilton.com")
                .latitude("45.23765")
                .longitude("48.58233")
                .features(new Features[]{Features.WIFI, Features.BREAKFAST})
                .build();
        long countBeforeCreate = hotelRepository.readAll().size();
        hotelRepository.create(hotel);
        long countAfterCreate = hotelRepository.readAll().size();
        assertEquals(1, countAfterCreate - countBeforeCreate);
    }

    @Test
    @Transactional
    public void readAllHotelsTest() {
        long hotelsNative = hotelRepository.readAll().size();
        long hotelCriteria = hotelRepository.readList(HotelSearchParameters.builder()
                .build())
                .size();
        assertEquals(hotelsNative, hotelCriteria);
    }

    @Test
    @Transactional
    public void readHotelTest() {
        Hotel hotel = hotelRepository.readList(HotelSearchParameters.builder()
                .id(25)
                .build())
                .get(0);
        assertEquals("Genappe", hotel.getName());
    }

    @Test
    @Transactional
    public void updateHotelTest() {
        Hotel hotel = Hotel.builder()
                .id(5)
                .name("Hilton")
                .stars(5)
                .website("hilton.com")
                .latitude("45.23765")
                .longitude("48.58233")
                .features(new Features[]{Features.BALCONY, Features.WIFI, Features.BREAKFAST})
                .build();
        hotelRepository.update(hotel);
        Hotel updated = hotelRepository.readList(HotelSearchParameters.builder()
                .id(hotel.getId())
                .build())
                .get(0);
        assertEquals(hotel.getName(), updated.getName());
    }

}