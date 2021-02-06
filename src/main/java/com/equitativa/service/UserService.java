package com.equitativa.service;

import com.equitativa.service.domain.User;

import java.util.List;

public interface UserService {
    Integer createUser(User user);

    List<User> getUsers();
}
