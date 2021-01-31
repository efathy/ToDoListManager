package com.equitativa.service.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String name;

    private boolean admin;
}
