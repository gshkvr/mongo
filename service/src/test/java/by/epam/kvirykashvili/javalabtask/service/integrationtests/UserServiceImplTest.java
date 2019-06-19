package by.epam.kvirykashvili.javalabtask.service.integrationtests;

import by.epam.kvirykashvili.javalabtask.domain.model.User;
import by.epam.kvirykashvili.javalabtask.domain.model.UserRole;
import by.epam.kvirykashvili.javalabtask.domain.parameters.UserSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.UserService;
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
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void createUserTest() {
        User user = User.builder()
                .login("tourist")
                .password("124sad21")
                .userRole(UserRole.MEMBER)
                .build();
        long countBeforeCreate = userService.readAll().size();
        userService.create(user);
        long countAfterCreate = userService.readAll().size();
        assertEquals(1, countAfterCreate - countBeforeCreate);
    }

    @Test
    public void readAllUsersTest() {
        long usersNamed = userService.readAll().size();
        long usersCriteria = userService.readList(UserSearchParameters.builder()
                .build())
                .size();
        assertEquals(usersNamed, usersCriteria);
    }

    @Test
    public void readUserTestTrue() {
        User user = userService.readList(UserSearchParameters.builder()
                .id(1)
                .build())
                .get(0);
        assertEquals("user", user.getLogin());
    }

    @Test
    public void readUserTestFalse() {
        User user = userService.readList(UserSearchParameters.builder()
                .id(2)
                .build())
                .get(0);
        assertNotEquals("user", user.getLogin());
    }

    @Test
    public void updateUserTest() {
        User user = User.builder()
                .id(55)
                .login("testuser")
                .password("jasdb324")
                .userRole(UserRole.MEMBER)
                .build();
        userService.update(user);
        User updated = userService.readList(UserSearchParameters.builder()
                .id(user.getId())
                .build())
                .get(0);
        assertEquals(user.getLogin(), updated.getLogin());
    }

    @Test
    public void deleteUserTestTrue() {
        User user = User.builder()
                .id(101)
                .build();
        long countBeforeDelete = userService.readAll().size();
        userService.delete(user);
        long countAfterDelete = userService.readAll().size();
        assertEquals(1, countBeforeDelete - countAfterDelete);
    }

    @Test
    public void deleteUserTestFalse() {
        User user = User.builder()
                .id(107)
                .build();
        long countBeforeDelete = userService.readAll().size();
        userService.delete(user);
        long countAfterDelete = userService.readAll().size();
        assertNotEquals(1, countBeforeDelete - countAfterDelete);
    }
}