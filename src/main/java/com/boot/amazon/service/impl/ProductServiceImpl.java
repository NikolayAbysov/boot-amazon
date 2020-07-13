package com.boot.amazon.service.impl;

import com.boot.amazon.model.Product;
import com.boot.amazon.repository.ProductRepository;
import com.boot.amazon.service.ProductSevice;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductSevice {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findByproductIdFromSource(String productIdFromSource) {
        return productRepository.findByProductIdFromSource(productIdFromSource);
    }
}
