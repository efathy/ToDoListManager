package com.equitativa.dataaccess;

import com.equitativa.service.domain.User;

import java.util.List;

public interface UserDataAccess {

    Integer createUser(User user);

    void deleteUser(Integer userId);

    User findUser(Integer userId);

    List<User> getUsers(Integer projectId);
}
