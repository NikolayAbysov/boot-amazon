package com.boot.amazon.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductGetRequestDto {
    private List<String> productIdFromSource;

    public ProductGetRequestDto() {
        this.productIdFromSource = new ArrayList<>();
    }
}
