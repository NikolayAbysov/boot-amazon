package com.boot.amazon.service;

import com.boot.amazon.model.Product;
import java.util.Optional;

public interface ProductSevice {
    Product save(Product product);

    Optional<Product> findByproductIdFromSource(String productIdFromSource);
}
