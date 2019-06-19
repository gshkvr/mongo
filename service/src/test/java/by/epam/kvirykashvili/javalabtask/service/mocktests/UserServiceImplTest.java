package by.epam.kvirykashvili.javalabtask.service.mocktests;

import by.epam.kvirykashvili.javalabtask.domain.model.User;
import by.epam.kvirykashvili.javalabtask.domain.parameters.UserSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.UserService;
import by.epam.kvirykashvili.javalabtask.service.config.ServiceConfig;
import by.epam.kvirykashvili.javalabtask.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@ActiveProfiles("test")
public class UserServiceImplTest {

    private UserService userServiceMock;
    private List<User> users;
    private User user;

    @Before
    public void init() {
        userServiceMock = mock(UserServiceImpl.class);
        user = new User();
        user.setLogin("tourist");
        user.setPassword("124sad21");
        User user1 = User.builder()
                .id(100)
                .login("testuser")
                .password("jasdb324")
                .build();
        User user2 = User.builder()
                .id(100)
                .login("tourist")
                .password("17701438999")
                .build();
        User user3 = User.builder()
                .id(101)
                .login("tourist")
                .password("17701438999")
                .build();
        users = Arrays.asList(user1, user2, user3);
    }

    @Test
    public void createCountryMockTest() {
        userServiceMock.create(user);
        verify(userServiceMock).create(user);
    }

    @Test
    public void deleteCountryMockTest() {
        userServiceMock.delete(user);
        verify(userServiceMock).delete(user);
    }

    @Test
    public void updateCountryMockTest() {
        userServiceMock.update(user);
        verify(userServiceMock).update(user);
    }

    @Test
    public void readAllCountriesMockTest() {
        when(userServiceMock.readAll()).thenReturn(users);
        List<User> countryList = userServiceMock.readAll();
        assertEquals(3, countryList.size());
    }

    @Test
    public void readCountryMockTest() {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userServiceMock.readList(any())).thenReturn(userList);
        User u = userServiceMock.readList(UserSearchParameters.builder().id(1).build()).get(0);
        assertEquals("tourist", u.getLogin());
    }
}