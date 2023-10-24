package com.example.ecommerce.dto;

import com.example.ecommerce.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotNull
    private String productName;
    @NotNull
    private ProductCategory category;
    @NotNull
    private String imageUrl;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal stock;
    private String description;
}