package com.boot.amazon.controller;

import com.boot.amazon.dto.UserGetRequestDto;
import com.boot.amazon.dto.UserRequestChangePasswordDto;
import com.boot.amazon.mapper.UserMapper;
import com.boot.amazon.model.User;
import com.boot.amazon.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/admin/top-active-users")
    public UserGetRequestDto getTopActiveUsers(@RequestParam(defaultValue = "1000") int limit) {
        return userMapper.map(userService.getTopMostActiveUsers(limit));
    }

    @PutMapping("/admin/change-user-password/{userName}")
    public void changeUserPassword(@RequestBody UserRequestChangePasswordDto dto,
                                   @PathVariable String userName) {
        User user = userService.findByProfileName(userName).get();
        user = userMapper.map(dto, user);
        userService.save(user);
    }
}
