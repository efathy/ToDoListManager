package com.equitativa.service;

import com.equitativa.service.domain.Project;

import java.util.List;

public interface ProjectService {
    Integer createProject(Project project);

    Project findProject(Integer projectId);

    List<Project> getProjects(Integer managerId);
}
