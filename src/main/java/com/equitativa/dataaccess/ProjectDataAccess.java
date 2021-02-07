package com.equitativa.dataaccess;

import com.equitativa.service.domain.Project;

import java.util.List;

public interface ProjectDataAccess {

    Integer createProject(Project project);

    void deleteProject(Integer projectId);

    Project findProject(Integer projectId);

    List<Project> getProjects(Integer managerId);
}
