package com.boot.amazon.security.impl;

import com.boot.amazon.exception.AuthenticationException;
import com.boot.amazon.model.User;
import com.boot.amazon.security.AuthenticationService;
import com.boot.amazon.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserService userService,
                                     PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String profileName, String password) throws AuthenticationException {
        User user = userService.findByProfileName(profileName).orElseThrow(()
                -> new AuthenticationException("No such user"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    @Override
    public User register(String profileName, String password) {
        if (userService.findByProfileName(profileName).isPresent()) {
            throw new AuthenticationException("User already exists");
        }
        User user = new User();
        user.setProfileName(profileName);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return user;
    }
}
