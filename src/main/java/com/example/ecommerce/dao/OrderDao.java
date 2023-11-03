package com.example.ecommerce.dao;

import com.example.ecommerce.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId, BigDecimal totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
