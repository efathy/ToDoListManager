package com.equitativa.model.entities;

import com.equitativa.service.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity(name = "users")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UsersEntity extends BaseEntity {
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_admin")
    private boolean admin;

    @OneToMany(mappedBy = "assignedTo", targetEntity = TaskEntity.class)
    private List<TaskEntity> assignedTasks;

    public UsersEntity(User user) {
        if(user != null) {
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
        //TODO TASKS
        return user;
    }
}
