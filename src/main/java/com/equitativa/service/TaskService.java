package com.equitativa.service;

import com.equitativa.service.domain.Task;

import java.util.List;

public interface TaskService {
    Integer createTask(Task task);

    void deleteTask(Integer taskId);

    void updateTask(Integer taskId, Task task);

    Task findTask(Integer taskId);

    List<Task> getTasks(Integer projectId, Integer assignedToId);
}
