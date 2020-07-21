package com.boot.amazon.dto;

import lombok.Data;

@Data
public class ReviewRequestAddDto {
    private String productId;
    private String profileName;
    private int score;
    private String summary;
    private String text;
}
