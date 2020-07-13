package com.boot.amazon.service;

import com.boot.amazon.model.Role;

import java.util.Optional;

public interface RoleService {
    Role save(Role role);

    Optional<Role> getByRoleName(String roleName);
}
