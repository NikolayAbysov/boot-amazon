package com.boot.amazon.mapper;

import com.boot.amazon.dto.CsvRecordDto;
import com.boot.amazon.dto.ReviewRequestDto;
import com.boot.amazon.model.Product;
import com.boot.amazon.model.Review;
import com.boot.amazon.model.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public Review map(CsvRecordDto csvRecordDto, User user, Product product) {
        Review review = new Review();
        review.setId(csvRecordDto.getId());
        review.setSummary(csvRecordDto.getSummary());
        review.setText(csvRecordDto.getText());
        review.setScore(csvRecordDto.getScore());
        review.setUser(user);
        review.setProduct(product);
        review.setTime(csvRecordDto.getLocalDate());
        return review;
    }

    public ReviewRequestDto map(List<String> words) {
        ReviewRequestDto dto = new ReviewRequestDto();
        dto.setWords(words);
        return dto;
    }
}
