package com.boot.amazon.service;

import com.boot.amazon.model.Review;
import java.util.List;
import java.util.Set;

public interface ReviewService {
    Review save(Review review);

    void saveAll(Set<Review> reviews);

    void deleteById(Long id);

    Review getById(Long id);

    List<String> getTopMostFrequentWordsInReview(int limit);
}
