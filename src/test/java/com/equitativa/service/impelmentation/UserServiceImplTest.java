package com.equitativa.service.impelmentation;

import com.equitativa.dataaccess.UserDataAccess;
import com.equitativa.dataaccess.implementation.UserDataAccessImpl;
import com.equitativa.dataaccess.utils.ConnectionHandler;
import com.equitativa.service.UserService;
import com.equitativa.service.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private final UserDataAccess userDataAccess = new UserDataAccessImpl();
    private final UserService userService = new UserServiceImpl();

    @BeforeAll
    static void beforeAll() {
        ConnectionHandler.initialize();
    }

    @Test
    void createUser() {
        User user = new User();
        user.setName("Eslam");
        user.setAdmin(true);
        Integer userId = userService.createUser(user);
        assertNotNull(userId);
        userDataAccess.deleteUser(userId);
    }

    @Test
    void getUsers() {
        User user = new User();
        user.setName("Eslam");
        user.setAdmin(true);
        Integer userId = userDataAccess.createUser(user);
        List<User> users = userService.getUsers();
        assertTrue(users != null && users.size() > 0);
        userDataAccess.deleteUser(userId);
    }
}