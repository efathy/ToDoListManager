package com.equitativa.dataaccess.implementation;

import com.equitativa.dataaccess.UserDataAccess;
import com.equitativa.dataaccess.utils.ConnectionHandler;
import com.equitativa.service.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class UserDataAccessImplTest {

    private UserDataAccess userDataAccess = new UserDataAccessImpl();

    @BeforeAll
    static void beforeAll() {
        ConnectionHandler.initialize();
    }

    @Test
    void createUser() {
        User user = new User();
        user.setName("Eslam");
        user.setAdmin(true);
        Integer userId = userDataAccess.createUser(user);
        assertNotNull(userId);
        userDataAccess.deleteUser(userId);
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setName("Eslam");
        user.setAdmin(true);
        Integer userId = userDataAccess.createUser(user);
        assertNotNull(userId);
        userDataAccess.deleteUser(userId);
        assertNull(userDataAccess.findUser(userId));
    }

    @Test
    void findUser() {
        User user = new User();
        user.setName("Eslam");
        user.setAdmin(true);
        Integer userId = userDataAccess.createUser(user);
        assertNotNull(userId);
        assertNotNull(userDataAccess.findUser(userId));
        userDataAccess.deleteUser(userId);
    }

    @Test
    void getUsers() {
        User user = new User();
        user.setName("Eslam");
        user.setAdmin(true);
        Integer userId = userDataAccess.createUser(user);
        List<User> users = userDataAccess.getUsers(null);
        assertTrue( users != null && users.size() > 0);
        userDataAccess.deleteUser(userId);
    }
}