package com.boot.amazon.repository;

import com.boot.amazon.model.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductIdFromSource(String productIdFromSource);
}
