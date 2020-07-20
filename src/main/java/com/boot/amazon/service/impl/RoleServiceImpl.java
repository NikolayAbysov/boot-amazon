package com.boot.amazon.service.impl;

import com.boot.amazon.model.Role;
import com.boot.amazon.repository.RoleRepository;
import com.boot.amazon.service.RoleService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getByRoleName(String roleName) {
        return roleRepository.getByRoleName(roleName);
    }
}
