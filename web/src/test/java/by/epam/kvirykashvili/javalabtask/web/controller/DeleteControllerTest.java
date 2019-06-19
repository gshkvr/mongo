package by.epam.kvirykashvili.javalabtask.web.controller;

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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, ServiceConfig.class, SecurityConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class DeleteControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    @Test
    public void removeReview() throws Exception {
        mvc.perform(post("/delete-review")
                .param("reviewId", "10")
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tours"));
    }

    @Test
    public void removeTour() throws Exception {
        mvc.perform(post("/delete-tour")
                .param("tourId", "10")
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tours"));
    }

    @Test
    public void removeCountry() throws Exception {
        mvc.perform(post("/delete-country")
                .param("countryId", "10")
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/countries"));
    }

    @Test
    public void removeUser() throws Exception {
        mvc.perform(post("/delete-user")
                .param("userId", "10")
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users"));
    }

    @Test
    public void removeHotel() throws Exception {
        mvc.perform(post("/delete-hotel")
                .param("hotelId", "10")
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/hotels"));
    }
}