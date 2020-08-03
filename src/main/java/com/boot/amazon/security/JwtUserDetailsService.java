package com.boot.amazon.security;

import com.boot.amazon.model.Role;
import com.boot.amazon.model.User;
import com.boot.amazon.service.UserService;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String profileName) throws UsernameNotFoundException {
        Optional<User> userOptional = findUserByUsername(profileName);

        org.springframework.security.core.userdetails.User.UserBuilder builder;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String[] roles = user.getRoles().stream()
                    .map(Role::getRoleName)
                    .toArray(String[]::new);
            builder = org.springframework.security.core.userdetails.User.withUsername(profileName);
            builder.password(user.getPassword());
            builder.roles(roles);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }

    private Optional<User> findUserByUsername(String username) {
        return userService.findByProfileName(username);
    }
}
