package by.epam.kvirykashvili.javalabtask.web.configuration;

import by.epam.kvirykashvili.javalabtask.service.*;
import by.epam.kvirykashvili.javalabtask.web.controller.*;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class TestConfig {
    @Bean
    public TourService tourService() {
        return Mockito.mock(TourService.class);
    }

    @Bean
    public CountryService countryService() {
        return Mockito.mock(CountryService.class);
    }

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public ReviewService reviewService() {
        return Mockito.mock(ReviewService.class);
    }

    @Bean
    public HotelService hotelService() {
        return Mockito.mock(HotelService.class);
    }

    @Bean
    public CountryController getController() {
        return new CountryController();
    }

    @Bean
    public HotelController createController() {
        return new HotelController();
    }

    @Bean
    public ReviewController deleteController() {
        return new ReviewController();
    }

    @Bean
    public TourController updateController() {
        return new TourController();
    }

    @Bean
    public UserController importController() {
        return new UserController();
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}