package com.boot.amazon.repository;

import com.boot.amazon.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductRepositoryTest {
    private Product product;

    @Autowired
     ProductRepository productRepository;

    @BeforeEach
    void init() {
        product = new Product();
        product.setProductIdFromSource("R1E3");
    }

    @Test
    void saveProductOk() {
        product = productRepository.save(product);
        Assertions.assertEquals(productRepository.findById(product.getId()).get(),
                product, "Products should be equal");
    }

    @Test
    void findByProductIdFromSourceOk() {
        product = productRepository.save(product);
        Assertions.assertEquals(productRepository.findByProductIdFromSource(product.getProductIdFromSource()).get(),
                product, "Products should be equal");
    }
}
