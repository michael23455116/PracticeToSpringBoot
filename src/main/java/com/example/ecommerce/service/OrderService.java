package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateOrderRequest;

public interface OrderService {
    Integer createOrder(Integer orderId, CreateOrderRequest createOrderRequest);
}
