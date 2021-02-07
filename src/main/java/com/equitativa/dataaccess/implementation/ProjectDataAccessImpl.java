package com.equitativa.dataaccess.implementation;

import com.equitativa.dataaccess.ProjectDataAccess;
import com.equitativa.dataaccess.utils.CRUDHelper;
import com.equitativa.model.entities.ProjectEntity;
import com.equitativa.service.domain.Project;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j
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
        delete(projectId);
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
        List<ProjectEntity> projectEntities = null;
        EntityManager entityManager = getNewEntityManager();
        try {
            if (managerId == null) {
                projectEntities = entityManager.createNamedQuery(ProjectEntity.FIND_ALL, ProjectEntity.class)
                                               .getResultList();
            } else {
                projectEntities = entityManager.createNamedQuery(ProjectEntity.FIND_BY_MANAGER, ProjectEntity.class)
                                               .setParameter("managerId", managerId)
                                               .getResultList();
            }
        } catch (Exception e) {
            log.error(DATABASE_EXCEPTION + e.getMessage(), e);
        } finally {
            entityManager.close();
        }

        if (!Optional.ofNullable(projectEntities).map(List::isEmpty).orElse(true)) {
            return projectEntities.stream().map(ProjectEntity::getProject).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
