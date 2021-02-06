package com.equitativa.service.impelmentation;

import com.equitativa.dataaccess.ProjectDataAccess;
import com.equitativa.dataaccess.implementation.ProjectDataAccessImpl;
import com.equitativa.service.ProjectService;
import com.equitativa.service.domain.Project;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    private ProjectDataAccess projectDataAccess;

    public ProjectServiceImpl() {
        this.projectDataAccess = new ProjectDataAccessImpl();
    }

    public ProjectServiceImpl(ProjectDataAccess projectDataAccess) {
        this.projectDataAccess = projectDataAccess;
    }

    @Override
    public Integer createProject(Project project) {
        return projectDataAccess.createProject(project);
    }

    @Override
    public List<Project> getProjects(Integer managerId) {
        return projectDataAccess.getProjects(managerId);
    }
}
