package com.boot.amazon.mapper;

import com.boot.amazon.dto.CsvRecordDto;
import com.boot.amazon.model.Review;
import com.boot.amazon.service.ProductSevice;
import com.boot.amazon.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    private final ProductSevice productSevice;
    private final UserService userService;

    public ReviewMapper(ProductSevice productSevice, UserService userService) {
        this.productSevice = productSevice;
        this.userService = userService;
    }

    public Review map(CsvRecordDto csvRecordDto) {
        Review review = new Review();
        review.setId(csvRecordDto.getId());
        review.setSummary(csvRecordDto.getSummary());
        review.setText(csvRecordDto.getText());
        review.setScore(csvRecordDto.getScore());
        review.setUser(userService
                .findByProfileName(csvRecordDto.getProfileName()));
        review.setProduct(productSevice
                .findByproductIdFromSource(csvRecordDto.getProductId()).get());
        review.setTime(csvRecordDto.getLocalDate());
        return review;
    }
}
