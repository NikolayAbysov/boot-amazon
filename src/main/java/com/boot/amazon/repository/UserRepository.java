package com.boot.amazon.repository;

import com.boot.amazon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByProfileName(String profileName);
}
