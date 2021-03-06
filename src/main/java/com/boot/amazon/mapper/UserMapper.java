package com.boot.amazon.mapper;

import com.boot.amazon.dto.CsvRecordDto;
import com.boot.amazon.dto.UserGetRequestDto;
import com.boot.amazon.dto.UserRequestChangePasswordDto;
import com.boot.amazon.model.User;
import com.boot.amazon.service.RoleService;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserMapper(RoleService roleService,
                      PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public User map(CsvRecordDto csvRecordDto) {
        User user = new User();
        user.setProfileName(csvRecordDto.getProfileName());
        user.getRoles().add(roleService.getByRoleName("USER").get());
        return user;
    }

    public User map(UserRequestChangePasswordDto dto, User user) {
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        return user;
    }

    public UserGetRequestDto map(List<String> names) {
        UserGetRequestDto dto = new UserGetRequestDto();
        for (String name : names) {
            dto.getProfileNames().add(name);
        }
        return dto;
    }
}
