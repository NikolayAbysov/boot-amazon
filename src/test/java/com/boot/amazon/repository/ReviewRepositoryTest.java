package com.boot.amazon.repository;

import com.boot.amazon.model.Product;
import com.boot.amazon.model.Review;
import com.boot.amazon.model.Role;
import com.boot.amazon.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTest {
    private Review review;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @BeforeEach
    void init() {
        Role role = Role.of("USER");
        role = roleRepository.save(role);
        User user = new User();
        user.setRoles(Set.of(Role.of("USER")));
        user.setProfileName("Shion");
        user.setRoles(Set.of(role));
        Product product = new Product();
        product.setProductIdFromSource("R1E3");
        review = new Review();
        review.setTime(LocalDate.now());
        review.setProduct(productRepository.save(product));
        review.setUser(userRepository.save(user));
        review.setScore(2);
        review.setText("text");
        review.setSummary("summary");
        review.setId(1L);
    }

    @Test
    void saveReviewOk() {
        review = reviewRepository.save(review);
        assertEquals(reviewRepository.findById(review.getId()).get(),
                review, "Reviews should be equal");
    }
}
