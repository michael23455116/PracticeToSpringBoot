package com.example.ecommerce.dao;

import com.example.ecommerce.dto.OrderQueryParameters;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDao {
    Integer countOrder(OrderQueryParameters orderQueryParameters);
    List<Order> getOrders(OrderQueryParameters orderQueryParameters);
    Integer createOrder(Integer userId, BigDecimal totalAmount);
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
    Order getOrderById(Integer orderId);
}
