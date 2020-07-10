package com.boot.amazon.mapper;

import com.boot.amazon.dto.CsvRecordDto;
import com.boot.amazon.model.Role;
import com.boot.amazon.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User map(CsvRecordDto csvRecordDto) {
        User user = new User();
        user.setProfileName(csvRecordDto.getProfileName());
        user.getRoles().add(Role.of("USER"));
        return user;
    }
}
