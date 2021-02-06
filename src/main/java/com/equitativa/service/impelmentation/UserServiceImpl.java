package com.equitativa.service.impelmentation;

import com.equitativa.dataaccess.UserDataAccess;
import com.equitativa.dataaccess.implementation.UserDataAccessImpl;
import com.equitativa.service.UserService;
import com.equitativa.service.domain.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDataAccess userDataAccess;

    public UserServiceImpl() {
        this.userDataAccess = new UserDataAccessImpl();
    }

    public UserServiceImpl(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    @Override
    public Integer createUser(User user) {
        return userDataAccess.createUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userDataAccess.getUsers();
    }
}
