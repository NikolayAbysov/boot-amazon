package com.boot.amazon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CSVRecordDto extends AbstractRecordDto {
    private int id;
    private String productId;
    private String userId;
    private String ProfileName;
    private int HelpfulnessNumerator;
    private int HelpfulnessDenominator;
    private int Score;
    private long Time;
    private String Summary;
    private String Text;
}
