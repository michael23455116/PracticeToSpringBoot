package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateOrderRequest;
import com.example.ecommerce.dto.OrderQueryParameters;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;

import java.util.List;

public interface OrderService {
    Integer countOrder(OrderQueryParameters orderQueryParameters);
    List<Order> getOrders(OrderQueryParameters orderQueryParameters);
    Integer createOrder(Integer orderId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
}
