package com.boot.amazon.controller;

import com.boot.amazon.model.Role;
import com.boot.amazon.model.User;
import com.boot.amazon.service.RoleService;
import javax.annotation.PostConstruct;

import com.boot.amazon.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class InjectDataController {
    private final RoleService roleService;
    private final UserService userService;

    public InjectDataController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    private void inject() {
        initRoles();
        addAdminUser();
    }

    private void initRoles() {
        Role user = Role.of("USER");
        Role admin = Role.of("ADMIN");
        roleService.save(user);
        roleService.save(admin);
    }

    private void addAdminUser() {
        User user = new User();
        user.setProfileName("admin");
        user.setRoles(Set.of(roleService.getByRoleName("ADMIN").get()));
        user.setPassword("admin");
        userService.save(user);
    }
}
