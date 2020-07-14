package com.boot.amazon.mapper;

import com.boot.amazon.dto.CsvRecordDto;
import com.boot.amazon.dto.ProductGetRequestDto;
import com.boot.amazon.model.Product;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product map(CsvRecordDto csvRecordDto) {
        Product product = new Product();
        product.setProductIdFromSource(csvRecordDto.getProductId());
        return product;
    }

    public ProductGetRequestDto map(List<String> products) {
        ProductGetRequestDto dto = new ProductGetRequestDto();
        for (String product : products) {
            dto.getProductIdFromSource().add(product);
        }
        return dto;
    }
}
