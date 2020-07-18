package com.boot.amazon.controller;

import com.boot.amazon.dto.ReviewRequestDto;
import com.boot.amazon.mapper.ReviewMapper;
import com.boot.amazon.service.ReviewService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @GetMapping("/top-words")
    public ReviewRequestDto getWords(@RequestParam(defaultValue = "1000") int limit) {
        List<String> words = reviewService.getTopMostFrequentWordsInReview(limit);
        return reviewMapper.map(words);
    }
}
