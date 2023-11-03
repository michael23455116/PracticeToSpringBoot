package com.example.ecommerce.dto;

import lombok.Data;

@Data
public class OrderQueryParameters {
    private Integer userId;
    private Integer limit;
    private Integer offset;
}
