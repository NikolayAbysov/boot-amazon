package com.boot.amazon.repository;

import com.boot.amazon.model.Role;
import com.boot.amazon.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    private User user;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void init() {
        Role role = Role.of("ADMIN");
        role = roleRepository.save(role);
        user = new User();
        user.setRoles(Set.of(Role.of("ADMIN")));
        user.setProfileName("Benio");
        user.setRoles(Set.of(role));
    }

    @Test
    void saveUserOk() {
        user = userRepository.save(user);
        assertEquals(userRepository.findByProfileName(user.getProfileName()),
                user, "Users should be equal");
    }
}
