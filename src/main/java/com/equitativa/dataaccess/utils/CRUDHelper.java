package com.equitativa.dataaccess.utils;

import com.equitativa.dataaccess.DatabaseException;
import com.equitativa.model.entities.BaseEntity;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import java.util.List;

@Log4j
public class CRUDHelper<T extends BaseEntity> {

    public static final String DATABASE_EXCEPTION = "Exception happen on dealing with database:";
    private Class<T> persistentClassType;

    public CRUDHelper(Class<T> type) {
        this.persistentClassType = type;
    }

    public Integer save(T entity) {
        EntityManager entityManager = getNewEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (entity.getId() == null) {
                entityManager.persist(entity);
            } else {
                entityManager.merge(entity);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.error(DATABASE_EXCEPTION + e.getMessage(), e);
            throw new DatabaseException(DATABASE_EXCEPTION + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return entity.getId();
    }

    public List<T> getEntities() {
        List<T> resultList = null;
        EntityManager entityManager = getNewEntityManager();
        try {
            resultList = entityManager.createQuery("Select t from " + getEntityName() + " t").getResultList();
        } catch (Exception e) {
            log.error(DATABASE_EXCEPTION + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return resultList;
    }

    private String getEntityName() {
        return persistentClassType.getSimpleName().replace("Entity", "").toLowerCase();
    }

    public void delete(Integer entityId) {
        // TODO: 1/31/21 FIX ME
        EntityManager entityManager = getNewEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(find(entityId));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.error(DATABASE_EXCEPTION + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    public T find(Integer entityId) {
        EntityManager entityManager = getNewEntityManager();
        try {
            return entityManager.find(persistentClassType, entityId);
        } catch (Exception e) {
            log.error(DATABASE_EXCEPTION + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
        return null;
    }

    public EntityManager getNewEntityManager() {
        return ConnectionHandler.getInstance().getNewEntityManager();
    }
}
