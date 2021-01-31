package com.equitativa.dataaccess.implementation;

import com.equitativa.dataaccess.UserDataAccess;
import com.equitativa.dataaccess.utils.CRUDHelper;
import com.equitativa.model.entities.UsersEntity;
import com.equitativa.service.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDataAccessImpl extends CRUDHelper<UsersEntity> implements UserDataAccess {

    public UserDataAccessImpl() {
        super(UsersEntity.class);
    }

    @Override
    public Integer createUser(User user) {
        return save(new UsersEntity(user));
    }

    @Override
    public void deleteUser(Integer userId) {
        delete(userId);
    }

    @Override
    public User findUser(Integer userId) {
        UsersEntity usersEntity = find(userId);
        if (usersEntity != null) {
            return usersEntity.getUser();
        }
        return null;
    }

    @Override
    public List<User> getUsers(Integer projectId) {
        // TODO: 1/31/21 project id
        List<UsersEntity> userEntities = getEntities();
        if (userEntities != null) {
            return userEntities.stream().map(UsersEntity::getUser).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
