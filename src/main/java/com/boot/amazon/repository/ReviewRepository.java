package com.boot.amazon.repository;

import com.boot.amazon.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "select text from review", nativeQuery = true)
    List<String> getTextFromReview();

}
