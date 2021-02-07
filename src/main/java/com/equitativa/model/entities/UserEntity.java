package com.equitativa.model.entities;

import com.equitativa.service.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity(name = "users")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@NamedQuery(name = UserEntity.FIND_ALL, query = "SELECT u FROM users u")
public class UserEntity extends BaseEntity {

    @Transient
    public static final String FIND_ALL = "UserEntity.findAll";

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_admin")
    private boolean admin;

    @OneToMany(mappedBy = "assignedTo", targetEntity = TaskEntity.class)
    private List<TaskEntity> assignedTasks;

    public UserEntity(User user) {
        if (user != null) {
            this.setId(user.getId());
            this.setName(user.getName());
            this.setAdmin(user.isAdmin());
        }
    }

    public User getUser() {
        User user = new User();
        user.setId(this.getId());
        user.setName(this.getName());
        user.setAdmin(this.isAdmin());
        return user;
    }
}
