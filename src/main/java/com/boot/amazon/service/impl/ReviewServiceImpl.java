package com.boot.amazon.service.impl;

import com.boot.amazon.model.Review;
import com.boot.amazon.repository.ReviewRepository;
import com.boot.amazon.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }
}
