package by.epam.kvirykashvili.javalabtask.web.controller;

import by.epam.kvirykashvili.javalabtask.domain.model.User;
import by.epam.kvirykashvili.javalabtask.domain.model.UserRole;
import by.epam.kvirykashvili.javalabtask.service.config.ServiceConfig;
import by.epam.kvirykashvili.javalabtask.web.config.SecurityConfig;
import by.epam.kvirykashvili.javalabtask.web.configuration.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, ServiceConfig.class, SecurityConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class CreateControllerTest {

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
    public void registerUser() throws Exception {
        mvc.perform(post("/register-user").with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void createUserGet() throws Exception {
        mvc.perform(get("/create-user").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("add/add-user"))
                .andExpect(view().name("add/add-user"));
    }

    @Test
    public void createUserPost() throws Exception {
        mvc.perform(get("/create-user").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("add/add-user"))
                .andExpect(view().name("add/add-user"));
    }

    @Test
    public void createCountryGet() throws Exception {
        mvc.perform(get("/create-country").with(user("admin").roles("MEMBER")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("add/add-country"))
                .andExpect(view().name("add/add-country"));
    }

    @Test
    public void createCountryPost() throws Exception {
        mvc.perform(get("/create-country").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("add/add-country"))
                .andExpect(view().name("add/add-country"));
    }

    @Test
    public void createReviewGet() throws Exception {
        mvc.perform(get("/create-review/{tourId}", 1).with(user(User.builder().id(1).login("user").userRole(UserRole.MEMBER).build())))
                .andExpect(status().isOk())
                .andExpect(view().name("add/add-review"));
    }

    @Test
    public void createTourGet() throws Exception {
        mvc.perform(get("/create-tour").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("add/add-tour"))
                .andExpect(view().name("add/add-tour"));
    }

    @Test
    public void createTourPost() throws Exception {
        mvc.perform(get("/create-tour").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("add/add-tour"))
                .andExpect(view().name("add/add-tour"));
    }

    @Test
    public void createHotelGet() throws Exception {
        mvc.perform(get("/create-hotel").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("add/add-hotel"))
                .andExpect(view().name("add/add-hotel"));
    }

    @Test
    public void createHotelPost() throws Exception {
        mvc.perform(get("/create-hotel").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("add/add-hotel"))
                .andExpect(view().name("add/add-hotel"));
    }
}