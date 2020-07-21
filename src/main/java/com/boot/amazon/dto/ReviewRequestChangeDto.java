package com.boot.amazon.dto;

import lombok.Data;

@Data
public class ReviewRequestChangeDto {
    private String profileName;
    private Long id;
    private int score;
    private String summary;
    private String text;
}
