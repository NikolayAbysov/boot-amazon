package com.boot.amazon.repository;

import com.boot.amazon.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getByRoleName(String roleName);
}
