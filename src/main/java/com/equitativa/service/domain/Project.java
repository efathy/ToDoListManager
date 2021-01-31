package com.equitativa.service.domain;

import lombok.Data;

import java.util.List;

@Data
public class Project {
    private Integer id;

    private String name;

    private User manager;

    List<Task> tasksList;
}
