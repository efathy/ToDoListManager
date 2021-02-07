package com.equitativa.dataaccess;

import com.equitativa.service.domain.Task;

import java.util.List;

public interface TaskDataAccess {

    Integer createTask(Task task);

    void updateTask(Integer taskId, Task task);

    void deleteTask(Integer taskId);

    Task findTask(Integer taskId);

    List<Task> getTasks(Integer projectId, Integer assignedTo);
}
