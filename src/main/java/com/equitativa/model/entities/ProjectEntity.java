package com.equitativa.model.entities;

import com.equitativa.service.domain.Project;
import com.equitativa.service.domain.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity(name = "project")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@NamedQuery(name = ProjectEntity.FIND_ALL, query = "SELECT p FROM project p")
@NamedQuery(name = ProjectEntity.FIND_BY_MANAGER, query = "SELECT p FROM project p WHERE p.manager.id = :managerId")
public class ProjectEntity extends BaseEntity {
    @Transient
    public static final String FIND_ALL = "ProjectEntity.findAll";
    @Transient
    public static final String FIND_BY_MANAGER = "ProjectEntity.findByManager";

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @OneToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private UserEntity manager;

    @OneToMany(mappedBy = "project")
    private List<TaskEntity> tasks;

    public ProjectEntity(Project project) {
        if (project != null) {
            this.setId(project.getId());
            this.setName(project.getName());
            this.setManager(new UserEntity(project.getManager()));
        }
    }

    public Project getProject() {
        Project project = new Project();
        project.setId(this.getId());
        project.setName(this.getName());
        project.setManager(this.getManager().getUser());
        if(tasks != null) {
            project.setTasksList(tasks.stream().map(TaskEntity::getSimpleTask).collect(Collectors.toList()));
        }
        return project;
    }
}
