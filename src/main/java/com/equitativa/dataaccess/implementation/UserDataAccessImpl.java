package com.equitativa.dataaccess.implementation;

import com.equitativa.dataaccess.UserDataAccess;
import com.equitativa.dataaccess.utils.CRUDHelper;
import com.equitativa.model.entities.UserEntity;
import com.equitativa.service.domain.User;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j
public class UserDataAccessImpl extends CRUDHelper<UserEntity> implements UserDataAccess {

    public UserDataAccessImpl() {
        super(UserEntity.class);
    }

    @Override
    public Integer createUser(User user) {
        return save(new UserEntity(user));
    }

    @Override
    public void deleteUser(Integer userId) {
        delete(userId);
    }

    @Override
    public User findUser(Integer userId) {
        UserEntity userEntity = find(userId);
        if (userEntity != null) {
            return userEntity.getUser();
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        List<UserEntity> userEntities = null;
        EntityManager entityManager = getNewEntityManager();
        try {
            userEntities = entityManager.createNamedQuery(UserEntity.FIND_ALL, UserEntity.class).getResultList();
        } catch (Exception e) {
            log.error(DATABASE_EXCEPTION + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        if (!Optional.ofNullable(userEntities).map(List::isEmpty).orElse(true)) {
            return userEntities.stream().map(UserEntity::getUser).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
