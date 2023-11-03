package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CreateOrderRequest;
import com.example.ecommerce.dto.OrderQueryParameters;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>>getOrders(@PathVariable Integer userId,
                                                @RequestParam(defaultValue = "10")@Max(1000)@Min(0)Integer limit,
                                                @RequestParam(defaultValue = "0")@Min(0)Integer offset){
        OrderQueryParameters orderQueryParameters = new OrderQueryParameters();
        orderQueryParameters.setUserId(userId);
        orderQueryParameters.setLimit(limit);
        orderQueryParameters.setOffset(offset);
        //取得order list
        List<Order> orderList = orderService.getOrders(orderQueryParameters);
        //取得Orders總數
        Integer count = orderService.countOrder(orderQueryParameters);
        //分頁
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PostMapping("/users/{userId}/orders")
    private ResponseEntity<?>createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest){
        Integer orderId = orderService.createOrder(userId,createOrderRequest);
        Order order =orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
