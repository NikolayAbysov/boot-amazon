package com.boot.amazon.mapper;

import com.boot.amazon.dto.CsvRecordDto;
import com.boot.amazon.dto.ReviewRequestAddDto;
import com.boot.amazon.dto.ReviewRequestChangeDto;
import com.boot.amazon.dto.ReviewRequestDto;
import com.boot.amazon.model.Product;
import com.boot.amazon.model.Review;
import com.boot.amazon.model.User;
import com.boot.amazon.service.ReviewService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    private final ReviewService reviewService;

    public ReviewMapper(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public Review map(CsvRecordDto csvRecordDto, User user, Product product) {
        Review review = new Review();
        review.setSummary(csvRecordDto.getSummary());
        review.setText(csvRecordDto.getText());
        review.setScore(csvRecordDto.getScore());
        review.setUser(user);
        review.setProduct(product);
        review.setTime(csvRecordDto.getLocalDate());
        return review;
    }

    public Review map(ReviewRequestAddDto dto, User user, Product product) {
        Review review = new Review();
        review.setSummary(dto.getSummary());
        review.setText(dto.getText());
        review.setScore(dto.getScore());
        review.setTime(LocalDate.now());
        review.setUser(user);
        review.setProduct(product);
        return review;
    }

    public Review map(ReviewRequestChangeDto dto) {
        Review review = reviewService.getById(dto.getId());
        review.setSummary(dto.getSummary());
        review.setText(dto.getText());
        review.setScore(dto.getScore());
        return review;
    }

    public ReviewRequestDto map(List<String> words) {
        ReviewRequestDto dto = new ReviewRequestDto();
        dto.setWords(words);
        return dto;
    }
}
