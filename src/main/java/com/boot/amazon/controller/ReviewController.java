package com.boot.amazon.controller;

import com.boot.amazon.dto.ReviewRequestAddDto;
import com.boot.amazon.dto.ReviewRequestChangeDto;
import com.boot.amazon.dto.ReviewRequestDto;
import com.boot.amazon.exception.NoReviewException;
import com.boot.amazon.mapper.ReviewMapper;
import com.boot.amazon.model.Product;
import com.boot.amazon.model.Review;
import com.boot.amazon.model.User;
import com.boot.amazon.service.ProductSevice;
import com.boot.amazon.service.ReviewService;
import com.boot.amazon.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final UserService userService;
    private final ProductSevice productSevice;

    public ReviewController(ReviewService reviewService,
                            ReviewMapper reviewMapper, UserService userService,
                            ProductSevice productSevice) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
        this.userService = userService;
        this.productSevice = productSevice;
    }

    @GetMapping("/admin/top-words")
    public ReviewRequestDto getWords(@RequestParam(defaultValue = "1000") int limit) {
        List<String> words = reviewService.getTopMostFrequentWordsInReview(limit);
        return reviewMapper.map(words);
    }

    @DeleteMapping("/admin/delete")
    public void deleteReview(@RequestParam Long reviewId) {
        reviewService.deleteById(reviewId);
    }

    @PostMapping("/add")
    public void addReview(@RequestBody ReviewRequestAddDto requestAddDto) {
        User user = userService.findByProfileName(requestAddDto.getProfileName()).orElseThrow();
        Product product = productSevice
                .findByproductIdFromSource(requestAddDto.getProductId()).orElseThrow();
        reviewService.save(reviewMapper.map(requestAddDto, user, product));
    }

    @PostMapping("/change")
    public void changeReview(@RequestBody ReviewRequestChangeDto requestChangeDto) {
        Review review = reviewMapper.map(requestChangeDto);
        if (!requestChangeDto.getProfileName().equals(review.getUser().getProfileName())) {
            throw new NoReviewException("Review not found by Id");
        }
        reviewService.save(review);
    }
}
