package com.equitativa.model.listener;

import com.equitativa.model.entities.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


public class EntityModificationListener {

    @PrePersist
    private void beforeCreate(BaseEntity entity) {
        entity.setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    private void beforeUpdate(BaseEntity entity) {
        entity.setModifiedAt(LocalDateTime.now());
    }
}
