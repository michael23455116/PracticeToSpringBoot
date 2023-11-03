package com.example.ecommerce.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Order {
    private Integer orderId;
    private Integer userId;
    private BigDecimal totalAmount;
    private Date createdDate;
    private Date lastModifiedDate;
}
