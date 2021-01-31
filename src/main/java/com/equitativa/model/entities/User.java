package com.equitativa.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Data
@Entity(name = "user")
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_admin")
    private boolean admin;
}
