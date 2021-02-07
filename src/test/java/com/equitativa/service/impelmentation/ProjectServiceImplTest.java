package com.equitativa.service.impelmentation;

import com.equitativa.dataaccess.ProjectDataAccess;
import com.equitativa.dataaccess.UserDataAccess;
import com.equitativa.dataaccess.implementation.ProjectDataAccessImpl;
import com.equitativa.dataaccess.implementation.UserDataAccessImpl;
import com.equitativa.dataaccess.utils.ConnectionHandler;
import com.equitativa.service.ProjectService;
import com.equitativa.service.domain.Project;
import com.equitativa.service.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceImplTest {

    private final ProjectDataAccess projectDataAccess = new ProjectDataAccessImpl();
    private final ProjectService projectService = new ProjectServiceImpl();
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

        project = new Project();
        project.setName("Project 1");
        project.setManager(this.user);
        Integer projectId = projectService.createProject(this.project);
        project.setId(projectId);
    }

    @Test
    void createProject() {
        assertNotNull(project.getId());
    }

    @Test
    void getProjects() {
        List<Project> projects = projectService.getProjects(null);
        assertNotEquals(0, projects.size());
        projects = projectService.getProjects(user.getId());
        assertNotEquals(0, projects.size());
        projects = projectService.getProjects(user.getId() + 1);
        assertEquals(0, projects.size());
    }
}