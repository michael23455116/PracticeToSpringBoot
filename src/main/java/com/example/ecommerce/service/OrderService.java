package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateOrderRequest;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;

public interface OrderService {
    Integer createOrder(Integer orderId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
}
