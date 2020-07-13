package com.boot.amazon.controller;

import com.boot.amazon.model.Role;
import com.boot.amazon.service.RoleService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class InjectDataController {
    private final RoleService roleService;

    public InjectDataController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    private void inject() {
        initRoles();
    }

    private void initRoles() {
        Role user = Role.of("USER");
        Role admin = Role.of("ADMIN");
        roleService.save(user);
        roleService.save(admin);
    }
}
