package com.example.ecommerce.dto;

import com.example.ecommerce.constant.ProductCategory;
import lombok.Data;

@Data
public class ProductQueryParameter {
    private ProductCategory category;
    private String search;
}
