package com.equitativa.model.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity(name = "task")
public class Task extends BaseEntity {
    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    @NotNull
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @JoinColumn(name = "assigned_to", nullable = false)
    private User assignedTo;

    @NotNull
    @JoinColumn(name = "reporter", nullable = false)
    private User reporter;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status = TaskStatus.TO_DO;
}
