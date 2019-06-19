package by.epam.kvirykashvili.javalabtask.web.controller;

import by.epam.kvirykashvili.javalabtask.domain.model.*;
import by.epam.kvirykashvili.javalabtask.domain.parameters.HotelSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.ReviewSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.TourSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.UserSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.HotelService;
import by.epam.kvirykashvili.javalabtask.service.ReviewService;
import by.epam.kvirykashvili.javalabtask.service.TourService;
import by.epam.kvirykashvili.javalabtask.service.UserService;
import by.epam.kvirykashvili.javalabtask.service.config.ServiceConfig;
import by.epam.kvirykashvili.javalabtask.web.config.SecurityConfig;
import by.epam.kvirykashvili.javalabtask.web.configuration.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, ServiceConfig.class, SecurityConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class GetControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private UserService userService;
    @Autowired
    private TourService tourService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private ReviewService reviewService;

    private MockMvc mvc;

    private List<User> users = new ArrayList<>();
    private User user = new User();

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
        user.setLogin("login");
        user.setId(4);
        user.setUserRole(UserRole.ADMIN);
        users.add(user);
    }

    @Test
    public void getProfile() throws Exception {
        when(userService.readList(any(UserSearchParameters.class))).thenReturn(users);
        mvc.perform(get("/profile").with(user(user)))
                .andExpect(status().isOk())
                .andExpect(view().name("entity/user"))
                .andExpect(model().attribute("user", user));
    }

    @Test
    public void viewToursTrue() throws Exception {
        mvc.perform(get("/")).andExpect(view().name("listEntities/tours"));
    }

    @Test(expected = AssertionError.class)
    public void viewToursFalse() throws Exception {
        mvc.perform(get("/")).andExpect(view().name("listEntities/tour"));
    }

    @Test
    public void getTour() throws Exception {
        List<Tour> tours = Collections.singletonList(new Tour());
        when(tourService.readList(any(TourSearchParameters.class))).thenReturn(tours);
        mvc.perform(get("/tour/{tourId}", "100").with(user(user)))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("entity/tour"))
                .andExpect(model().attribute("tour", tours.get(0)));
    }

    @Test
    public void viewUsers() throws Exception {
        mvc.perform(get("/users")).andExpect(view().name("listEntities/users"));
    }

    @Test
    public void getUser() throws Exception {
        when(userService.readList(any(UserSearchParameters.class))).thenReturn(users);
        mvc.perform(get("/user/{tourId}", "4").with(user(user)))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("entity/user"))
                .andExpect(model().attribute("user", users.get(0)));
    }

    @Test
    public void viewCountries() throws Exception {
        mvc.perform(get("/countries")).andExpect(view().name("listEntities/countries"));
    }

    @Test
    public void viewHotels() throws Exception {
        mvc.perform(get("/hotels")).andExpect(view().name("listEntities/hotels"));
    }

    @Test
    public void getHotel() throws Exception {
        List<Hotel> hotels = Collections.singletonList(new Hotel());
        when(hotelService.readList(any(HotelSearchParameters.class))).thenReturn(hotels);
        mvc.perform(get("/hotel/{hotelId}", "100").with(user(user)))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("entity/hotel"))
                .andExpect(model().attribute("hotel", hotels.get(0)));
    }

    @Test
    public void viewReviews() throws Exception {
        mvc.perform(get("/reviews")).andExpect(view().name("listEntities/reviews"));
    }

    @Test
    public void getReview() throws Exception {
        List<Review> reviews = Collections.singletonList(new Review());
        when(reviewService.readList(any(ReviewSearchParameters.class))).thenReturn(reviews);
        mvc.perform(get("/review/{reviewId}", "100").with(user(user)))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("entity/review"))
                .andExpect(model().attribute("review", reviews.get(0)));
    }
}