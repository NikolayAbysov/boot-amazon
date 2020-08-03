package com.boot.amazon.dto;

import lombok.Data;

@Data
public class UserRequestChangePasswordDto {
    private String profileName;
    private String newPassword;
}
