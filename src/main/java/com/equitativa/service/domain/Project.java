package com.equitativa.service.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Project implements Serializable {
    private Integer id;

    private String name;

    private User manager;

    List<Task> tasksList;
}
