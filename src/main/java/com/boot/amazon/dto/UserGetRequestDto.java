package com.boot.amazon.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGetRequestDto {
    private List<String> profileNames;

    public UserGetRequestDto() {
        this.profileNames = new ArrayList<>();
    }
}
