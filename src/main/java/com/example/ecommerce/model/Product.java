package com.example.ecommerce.model;

import com.example.ecommerce.constant.ProductCategory;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class Product {
    private Integer productId;
    private String productName;
    private ProductCategory category;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;
    private String description;
    private Date createdDate;
    private Date lastModifiedDate;
}