package com.boot.amazon.controller;

import com.boot.amazon.dto.ProductGetRequestDto;
import com.boot.amazon.mapper.ProductMapper;
import com.boot.amazon.service.ProductSevice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductSevice productSevice;
    private final ProductMapper productMapper;

    public ProductController(ProductSevice productSevice, ProductMapper productMapper) {
        this.productSevice = productSevice;
        this.productMapper = productMapper;
    }

    @GetMapping("/top-thousand-most-commented")
    public ProductGetRequestDto getThousandTopCommentedItems() {
        return productMapper.map(productSevice.getTopThousand());
    }
}
