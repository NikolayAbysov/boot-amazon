package com.boot.amazon.service.impl;

import com.boot.amazon.model.User;
import com.boot.amazon.repository.UserRepository;
import com.boot.amazon.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByProfileName(String profileName) {
        return userRepository.findByProfileName(profileName);
    }
}
