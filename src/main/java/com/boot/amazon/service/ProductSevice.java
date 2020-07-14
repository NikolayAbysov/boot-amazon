package com.boot.amazon.service;

import com.boot.amazon.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductSevice {
    Product save(Product product);

    void saveAll(List<Product> products);

    Optional<Product> findByproductIdFromSource(String productIdFromSource);

    List<String> getTopThousand();
}
