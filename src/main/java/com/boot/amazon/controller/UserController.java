package com.boot.amazon.controller;

import com.boot.amazon.dto.UserGetRequestDto;
import com.boot.amazon.mapper.UserMapper;
import com.boot.amazon.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final int USERS_AMOUNT = 1000;
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/top-active-users")
    public UserGetRequestDto getTopActiveUsers(@RequestParam(defaultValue = "1000") int limit) {
        return userMapper.map(userService.getTopMostActiveUsers(limit));
    }
}
