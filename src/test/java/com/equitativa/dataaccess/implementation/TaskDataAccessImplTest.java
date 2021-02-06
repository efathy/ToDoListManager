package com.equitativa.dataaccess.implementation;

import com.equitativa.dataaccess.ProjectDataAccess;
import com.equitativa.dataaccess.TaskDataAccess;
import com.equitativa.dataaccess.UserDataAccess;
import com.equitativa.dataaccess.utils.ConnectionHandler;
import com.equitativa.service.domain.Project;
import com.equitativa.service.domain.Task;
import com.equitativa.service.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskDataAccessImplTest {

    private final ProjectDataAccess projectDataAccess = new ProjectDataAccessImpl();
    private final UserDataAccess userDataAccess = new UserDataAccessImpl();
    private final TaskDataAccess taskDataAccess = new TaskDataAccessImpl();

    private User user = null;
    private Project project = null;
    private Task task = null;

    @BeforeAll
    static void beforeAll() {
        ConnectionHandler.initialize();
    }

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Eslam");
        user.setAdmin(true);
        Integer userId = userDataAccess.createUser(this.user);
        user.setId(userId);

        System.out.println(user);
        project = new Project();
        project.setName("Project 1");
        project.setManager(this.user);
        Integer projectId = projectDataAccess.createProject(this.project);
        project.setId(projectId);

        task = new Task();
        task.setName("Task 1");
        task.setDescription("Desc 1");
        task.setProject(project);
        task.setAssignedTo(user);
        task.setReporter(user);
        Integer taskId = taskDataAccess.createTask(this.task);
        task.setId(taskId);
    }

    @AfterEach
    void tearDown() {
        taskDataAccess.deleteTask(task.getId());
        projectDataAccess.deleteProject(project.getId());
        userDataAccess.deleteUser(user.getId());
    }

    @Test
    void createTask() {
        assertNotNull(task.getId());
    }

    @Test
    void deleteTask() {
        Task task2 = new Task();
        task2.setName("Task 1");
        task2.setDescription("Desc 1");
        task2.setProject(project);
        task2.setAssignedTo(user);
        task2.setReporter(user);
        Integer taskId = taskDataAccess.createTask(task2);
        assertNotNull(taskId);
        taskDataAccess.deleteTask(taskId);
        assertNull(taskDataAccess.findTask(taskId));
    }

    @Test
    void findTask() {
        assertNotNull(taskDataAccess.findTask(task.getId()));
    }

    @Test
    void getTasks() {
        List<Task> tasks = taskDataAccess.getTasks(null, null);
        assertNotEquals(0, tasks.size());

        tasks = taskDataAccess.getTasks(project.getId(), null);
        assertNotEquals(0, tasks.size());

        tasks = taskDataAccess.getTasks(null, user.getId());
        assertNotEquals(0, tasks.size());

        tasks = taskDataAccess.getTasks(project.getId(), user.getId());
        assertNotEquals(0, tasks.size());

        tasks = taskDataAccess.getTasks(null, user.getId() + 1);
        assertEquals(0, tasks.size());
    }

    @Test
    void updateTask() {
        task.setName("Task 2");
        taskDataAccess.updateTask(task.getId(), task);
        assertEquals("Task 2", taskDataAccess.findTask(task.getId()).getName());
    }

}