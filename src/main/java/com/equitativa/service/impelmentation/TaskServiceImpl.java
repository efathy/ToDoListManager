package com.equitativa.service.impelmentation;

import com.equitativa.dataaccess.TaskDataAccess;
import com.equitativa.dataaccess.implementation.TaskDataAccessImpl;
import com.equitativa.service.TaskService;
import com.equitativa.service.domain.Task;

import java.util.List;

public class TaskServiceImpl implements TaskService {

    private TaskDataAccess taskDataAccess;

    public TaskServiceImpl() {
        this.taskDataAccess = new TaskDataAccessImpl();
    }

    public TaskServiceImpl(TaskDataAccess taskDataAccess) {
        this.taskDataAccess = taskDataAccess;
    }

    @Override
    public Integer createTask(Task task) {
        return taskDataAccess.createTask(task);
    }

    @Override
    public void deleteTask(Integer taskId) {
        taskDataAccess.deleteTask(taskId);
    }

    @Override
    public void updateTask(Integer taskId, Task task) {
        taskDataAccess.updateTask(taskId, task);
    }

    @Override
    public Task findTask(Integer taskId) {
        return taskDataAccess.findTask(taskId);
    }

    @Override
    public List<Task> getTasks(Integer projectId, Integer assignedToId) {
        return taskDataAccess.getTasks(projectId, assignedToId);
    }
}
