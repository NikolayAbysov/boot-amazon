package com.boot.amazon.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    private List<String> words;

    public ReviewRequestDto() {
        this.words = new ArrayList<>();
    }
}
