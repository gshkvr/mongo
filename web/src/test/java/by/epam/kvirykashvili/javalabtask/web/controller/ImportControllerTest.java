package by.epam.kvirykashvili.javalabtask.web.controller;

import by.epam.kvirykashvili.javalabtask.service.config.ServiceConfig;
import by.epam.kvirykashvili.javalabtask.web.config.SecurityConfig;
import by.epam.kvirykashvili.javalabtask.web.configuration.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, ServiceConfig.class, SecurityConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class ImportControllerTest {

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
    public void singleFileUploadTest_post() throws Exception {
        MockMultipartFile file = new MockMultipartFile("toursFile", "tours.csv", null, "photo,date,duration,description,cost,tour_type,hotel_id,country_id\n\"//photos/2138838923923\",2018-02-11,11,\"Cultural tour for 11 days\",400,\"CULTURAL\",38,26\n\"//photos/8579357925848\",2018-03-22,12,\"Cultural tour for 12 days\",1600,\"CULTURAL\",14,10".getBytes());
        mvc.perform(multipart("/import-tour")
                .file(file)
                .with(user("user").roles("ADMIN")))
                .andExpect(status().is(302))
                .andExpect(flash().attribute("message", "Tours from file were imported"))
                .andExpect(view().name("redirect:/import"));
    }

    @Test
    public void singleFileUploadTestFalse_post_emptyFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("toursFile", "tours.csv", null, "".getBytes());
        mvc.perform(multipart("/import-tour")
                .file(file)
                .with(user("user").roles("ADMIN")))
                .andExpect(status().is(302))
                .andExpect(flash().attribute("message", "Importing file was not selected"))
                .andExpect(view().name("redirect:/import"));
    }

    @Test
    public void singleFileUploadTestFalse_post_wrongFileType() throws Exception {
        MockMultipartFile file = new MockMultipartFile("toursFile", "tours.xml", null, "basdfsadfr".getBytes());
        mvc.perform(multipart("/import-tour")
                .file(file)
                .with(user("user").roles("ADMIN")))
                .andExpect(status().is(302))
                .andExpect(flash().attribute("message", "Importing file should be in .csv format"))
                .andExpect(view().name("redirect:/import"));
    }
}