package com.equitativa.dataaccess.implementation;

import com.equitativa.dataaccess.ProjectDataAccess;
import com.equitativa.dataaccess.UserDataAccess;
import com.equitativa.dataaccess.utils.ConnectionHandler;
import com.equitativa.service.domain.Project;
import com.equitativa.service.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDataAccessImplTest {

    private final ProjectDataAccess projectDataAccess = new ProjectDataAccessImpl();
    private final UserDataAccess userDataAccess = new UserDataAccessImpl();

    private User user = null;
    private Project project = null;

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
    }

    @AfterEach
    void tearDown() {
        projectDataAccess.deleteProject(project.getId());
        userDataAccess.deleteUser(user.getId());
    }

    @Test
    void createProject() {
        assertNotNull(project.getId());
    }

    @Test
    void deleteProject() {
        Project project2 = new Project();
        project2.setName("Eslam");
        project2.setManager(user);
        Integer projectId = projectDataAccess.createProject(project2);
        assertNotNull(projectId);
        projectDataAccess.deleteProject(projectId);
        assertNull(projectDataAccess.findProject(projectId));
    }

    @Test
    void findProject() {
        assertNotNull(projectDataAccess.findProject(project.getId()));
    }

    @Test
    void getProjects() {
        List<Project> projects = projectDataAccess.getProjects(null);
        assertNotEquals(0, projects.size());
        projects = projectDataAccess.getProjects(user.getId());
        assertNotEquals(0, projects.size());
        projects = projectDataAccess.getProjects(user.getId() + 1);
        assertEquals(0, projects.size());
    }
}