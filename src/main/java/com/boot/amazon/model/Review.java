package com.boot.amazon.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "\"review\"")
public class Review {
    @Id
    private Long id;
    private String summary;
    private String text;
    private int score;
    private User user;
    private Long productId;
    private LocalDateTime time;
    private int HelpfulnessNumerator;
    private int HelpfulnessDenominator;
}
