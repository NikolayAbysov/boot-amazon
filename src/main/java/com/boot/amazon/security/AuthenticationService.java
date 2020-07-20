package com.boot.amazon.security;


import com.boot.amazon.exception.AuthenticationException;
import com.boot.amazon.model.User;

public interface AuthenticationService {

    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}