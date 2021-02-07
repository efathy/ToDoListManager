package com.equitativa.service.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Task implements Serializable {

    private Integer id;

    private String name;

    private String description;

    private Project project;

    private User assignedTo;

    private User reporter;

    private TaskStatus status = TaskStatus.TO_DO;
}
