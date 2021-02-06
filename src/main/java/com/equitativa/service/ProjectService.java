package com.equitativa.service;

import com.equitativa.service.domain.Project;

import java.util.List;

public interface ProjectService {
    Integer createProject(Project project);

    List<Project> getProjects(Integer managerId);
}
