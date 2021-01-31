package com.equitativa.model.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Data
@Entity(name = "user")
public class User extends BaseEntity {
    @NotBlank
    @Column(name = "name")
    private String name;

    private boolean admin;

//    private
}
