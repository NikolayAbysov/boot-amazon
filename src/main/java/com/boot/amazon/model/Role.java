package com.boot.amazon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"role\"")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;

    public Role() {
    }

    public Role(RoleName roleName) {
        this.roleName = roleName.toString();
    }

    public static Role of(String roleName) {
        return new Role(RoleName.valueOf(roleName));
    }

    private enum RoleName {
        ADMIN, USER
    }
}
