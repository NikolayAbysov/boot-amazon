package com.boot.amazon.service;

import com.boot.amazon.model.User;

public interface UserService {
    User save(User user);

    User findByProfileName(String profileName);
}
