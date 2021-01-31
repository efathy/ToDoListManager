package com.equitativa.dataaccess.implementation;

import com.equitativa.dataaccess.ProjectDataAccess;
import com.equitativa.dataaccess.utils.CRUDHelper;
import com.equitativa.model.entities.ProjectEntity;
import com.equitativa.service.domain.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectDataAccessImpl extends CRUDHelper<ProjectEntity> implements ProjectDataAccess {

    public ProjectDataAccessImpl() {
        super(ProjectEntity.class);
    }

    @Override
    public Integer createProject(Project project) {
        return save(new ProjectEntity(project));
    }

    @Override
    public void deleteProject(Integer projectId) {
//        delete(projectId);
    }

    @Override
    public Project findProject(Integer projectId) {
        ProjectEntity projectEntity = find(projectId);
        if (projectEntity != null) {
            return projectEntity.getProject();
        }
        return null;
    }

    @Override
    public List<Project> getProjects(Integer managerId) {
        //TODO manager ID
        List<ProjectEntity> projectEntities = getEntities();
        if (projectEntities != null) {
            return projectEntities.stream().map(ProjectEntity::getProject).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
