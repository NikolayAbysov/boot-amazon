package com.boot.amazon.service.impl;

import com.boot.amazon.model.Review;
import com.boot.amazon.repository.ReviewRepository;
import com.boot.amazon.service.ReviewService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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

    @Override
    public void saveAll(Set<Review> reviews) {
        reviewRepository.saveAll(reviews);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Review getById(Long id) {
        return reviewRepository.getById(id);
    }

    @Override
    public List<String> getTopMostFrequentWordsInReview(int limit) {
        Map<String, Integer> wordsMap = getWordsMap(limit);
        return new ArrayList<>(wordsMap.keySet());
    }

    private Map<String, Integer> getWordsMap(int limit) {
        List<String> textFromReview = reviewRepository.getTextFromReview();
        Map<String, Integer> wordCount = new HashMap<>();

        for (String text : textFromReview) {
            String[] words = text.split("[ ]");
            for (String word : words) {
                if (wordCount.containsKey(word)) {
                    wordCount.put(word, wordCount.get(word) + 1);
                } else {
                    wordCount.put(word, 1);
                }
            }
        }
        return wordCount.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }
}
