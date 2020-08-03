package com.boot.amazon.controller;

import com.boot.amazon.model.Role;
import com.boot.amazon.model.User;
import com.boot.amazon.service.RoleService;
import com.boot.amazon.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InjectDataController {
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public InjectDataController(RoleService roleService,
                                UserService userService,
                                PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void inject() {
        if (roleService.getByRoleName("ADMIN").isEmpty()) {
            initRoles();
        }
        if (userService.findByProfileName("admin").isEmpty()) {
            addAdminUser();
        }
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
        user.setPassword(passwordEncoder.encode("admin"));
        userService.save(user);
    }
}
