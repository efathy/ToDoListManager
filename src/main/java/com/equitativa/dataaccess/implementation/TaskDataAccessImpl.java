package com.equitativa.dataaccess.implementation;

import com.equitativa.dataaccess.utils.CRUDHelper;
import com.equitativa.dataaccess.TaskDataAccess;
import com.equitativa.model.entities.TaskEntity;
import com.equitativa.service.domain.Task;

import java.util.List;

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
//        delete(taskId);
    }

    @Override
    public List<Task> getTasks(Integer projectId, Integer assignedTo) {
        //TODO GET TASKS
        return null;
    }
}
