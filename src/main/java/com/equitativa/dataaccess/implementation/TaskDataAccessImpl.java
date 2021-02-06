package com.equitativa.dataaccess.implementation;

import com.equitativa.dataaccess.TaskDataAccess;
import com.equitativa.dataaccess.utils.CRUDHelper;
import com.equitativa.model.entities.ProjectEntity;
import com.equitativa.model.entities.TaskEntity;
import com.equitativa.service.domain.Task;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j
public class TaskDataAccessImpl extends CRUDHelper<TaskEntity> implements TaskDataAccess {

    public TaskDataAccessImpl() {
        super(TaskEntity.class);
    }

    @Override
    public Integer createTask(Task task) {
        return save(new TaskEntity(task));
    }

    @Override
    public void updateTask(Integer taskId, Task task) {
        save(new TaskEntity(task));
    }

    @Override
    public void deleteTask(Integer taskId) {
        delete(taskId);
    }

    @Override
    public Task findTask(Integer taskId) {
        TaskEntity taskEntity = find(taskId);
        if (taskEntity != null) {
            return find(taskId).getTask();
        }
        return null;
    }

    @Override
    public List<Task> getTasks(Integer projectId, Integer assignedToId) {
        List<TaskEntity> taskEntities = null;
        EntityManager entityManager = getNewEntityManager();
        try {
            if (projectId != null && assignedToId != null) {
                taskEntities = entityManager
                        .createNamedQuery(TaskEntity.FIND_BY_PROJECT_AND_ASSIGNED_TO, TaskEntity.class)
                        .setParameter("projectId", projectId)
                        .setParameter("assignedToId", assignedToId)
                        .getResultList();
            } else if (assignedToId != null) {
                taskEntities = entityManager.createNamedQuery(TaskEntity.FIND_BY_ASSIGNED_TO, TaskEntity.class)
                                            .setParameter("assignedToId", assignedToId)
                                            .getResultList();
            } else if (projectId != null) {
                taskEntities = entityManager.createNamedQuery(TaskEntity.FIND_BY_PROJECT, TaskEntity.class)
                                            .setParameter("projectId", projectId)
                                            .getResultList();
            } else {
                taskEntities = entityManager.createNamedQuery(TaskEntity.FIND_ALL, TaskEntity.class)
                                            .getResultList();
            }
        } catch (Exception e) {
            log.error(DATABASE_EXCEPTION + e.getMessage(), e);
        } finally {
            entityManager.close();
        }

        if (!Optional.ofNullable(taskEntities).map(List::isEmpty).orElse(true)) {
            return taskEntities.stream().map(TaskEntity::getTask).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
