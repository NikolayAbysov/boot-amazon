package com.boot.amazon.mapper;

import com.boot.amazon.dto.CsvRecordDto;
import com.boot.amazon.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product map(CsvRecordDto csvRecordDto) {
        Product product = new Product();
        product.setProductIdFromSource(csvRecordDto.getProductId());
        return product;
    }
}
