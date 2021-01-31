package com.equitativa.service.domain;

import lombok.Data;

@Data
public class Task {

    private Integer id;

    private String name;

    private String description;

    private Project project;

    private User assignedTo;

    private User reporter;

    private TaskStatus status = TaskStatus.TO_DO;
}
