package com.boot.amazon.service;

import com.boot.amazon.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    void saveAll(List<User> users);

    Optional<User> findByProfileName(String profileName);

    List<String> getTopThousandMostActiveUsers();
}
