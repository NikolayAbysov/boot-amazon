package com.boot.amazon.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"review\"")
public class Review {
    @Id
    private Long id;
    private String summary;
    @Column(columnDefinition = "TEXT")
    private String text;
    private int score;
    @OneToOne
    private User user;
    @OneToOne
    private Product product;
    private LocalDate time;
}
