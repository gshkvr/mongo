package by.epam.kvirykashvili.javalabtask.web.converter;

import by.epam.kvirykashvili.javalabtask.domain.model.Country;
import by.epam.kvirykashvili.javalabtask.domain.model.Hotel;
import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.model.User;
import by.epam.kvirykashvili.javalabtask.domain.parameters.CountrySearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.HotelSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.TourSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.UserSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.CountryService;
import by.epam.kvirykashvili.javalabtask.service.HotelService;
import by.epam.kvirykashvili.javalabtask.service.TourService;
import by.epam.kvirykashvili.javalabtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class EntityConverter {

    @Autowired
    private TourService tourService;
    @Autowired
    private UserService userService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private HotelService hotelService;

    public Converter<String, Tour> getTourIdToTourConverter() {
        return new Converter<String, Tour>() {
            @Override
            public Tour convert(String id) {
                int tourId = Integer.valueOf(id);
                List<Tour> tourList = tourService.readList(TourSearchParameters.builder().id(tourId).build());
                if (tourList.size() > 0) {
                    return tourList.get(0);
                } else {
                    return null;
                }
            }
        };
    }

    public Converter<String, User> getUserIdToUserConverter() {
        return new Converter<String, User>() {
            @Override
            public User convert(String id) {
                int userId = Integer.valueOf(id);
                List<User> userList = userService.readList(UserSearchParameters.builder().id(userId).build());
                if (userList.size() > 0) {
                    return userList.get(0);
                } else {
                    return null;
                }
            }
        };
    }

    public Converter<String, Date> getDateConverter() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String date) {
                try {
                    return new SimpleDateFormat("dd/MM/yyyy").parse(date);
                } catch (ParseException e) {
                    return new Date();
                }
            }
        };
    }

    public Converter<String, Country> getCountryIdToCountryConverter() {
        return new Converter<String, Country>() {
            @Override
            public Country convert(String id) {
                int countryId = Integer.valueOf(id);
                List<Country> countryList = countryService.readList(CountrySearchParameters.builder().id(countryId).build());
                if (countryList.size() > 0) {
                    return countryList.get(0);
                } else {
                    return null;
                }
            }
        };
    }

    public Converter<String, Hotel> getHotelIdToHotelConverter() {
        return new Converter<String, Hotel>() {
            @Override
            public Hotel convert(String id) {
                int hotelId = Integer.valueOf(id);
                List<Hotel> hotelList = hotelService.readList(HotelSearchParameters.builder().id(hotelId).build());
                if (hotelList.size() > 0) {
                    return hotelList.get(0);
                } else {
                    return null;
                }
            }
        };
    }

}
