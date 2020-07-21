package com.boot.amazon.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvRecordDto extends AbstractRecordDto {
    private String productId;
    private String userId;
    private String profileName;
    private int helpfulnessNumerator;
    private int helpfulnessDenominator;
    private int score;
    private LocalDate localDate;
    private String summary;
    private String text;
}
