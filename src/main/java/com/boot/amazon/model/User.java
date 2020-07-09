package com.boot.amazon.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "\"user\"")
public class User {
    private String id;
    private String profileName;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
