package com.equitativa.model.entities;

import com.equitativa.service.domain.Task;
import com.equitativa.service.domain.TaskStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity(name = "task")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
// TODO: 1/31/21 Entity listener
public class TaskEntity extends BaseEntity {
    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    @NotNull
    @ManyToOne(targetEntity = ProjectEntity.class)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @ManyToOne(targetEntity = UsersEntity.class)
    @JoinColumn(name = "assigned_to", nullable = false)
    private UsersEntity assignedTo;

    @NotNull
    @ManyToOne(targetEntity = UsersEntity.class)
    @JoinColumn(name = "reporter", nullable = false)
    private UsersEntity reporter;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status = TaskStatus.TO_DO;

    public TaskEntity(Task task) {
        if (task != null) {
            this.setId(task.getId());
            this.setName(task.getName());
            this.setDescription(task.getDescription());
            this.setStatus(task.getStatus());
            this.setAssignedTo(new UsersEntity(task.getAssignedTo()));
            this.setReporter(new UsersEntity(task.getReporter()));
            this.setProject(new ProjectEntity(task.getProject()));
        }
    }

    public Task getTask() {
        Task task = new Task();
        task.setId(this.getId());
        task.setName(this.getName());
        task.setDescription(this.getDescription());
        task.setStatus(this.getStatus());
        task.setAssignedTo(this.getAssignedTo().getUser());
        task.setReporter(this.getReporter().getUser());
        task.setProject(this.getProject().getProject());
        return task;
    }
}
