package com.example.ecommerce.service.impl;

import com.example.ecommerce.dao.OrderDao;
import com.example.ecommerce.dao.ProductDao;
import com.example.ecommerce.dao.UserDao;
import com.example.ecommerce.dto.BuyItem;
import com.example.ecommerce.dto.CreateOrderRequest;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);
        order.setOrderItemList(orderItemList);
        return order;
    }

    @Override
    @Transactional
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        //檢查user是否存在
        User user = userDao.getUserById(userId);
        if (user == null) {
            log.warn("該userId{}不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

            BigDecimal totalAmount = BigDecimal.ZERO;
            List<OrderItem> orderItemList = new ArrayList<>();

            for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
                Product product = productDao.getProductById(buyItem.getProductId());

                //檢查商品是否存在，庫存是否足夠
                if (product==null){
                    log.warn("商品{}不存在",buyItem.getProductId());
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }else if (product.getStock().intValue() < buyItem.getQuantity()){
                    log.warn("商品{}庫存不足，無法購買。剩餘庫存{}，欲購買數量{}。",
                            buyItem.getProductId(),product.getStock(),buyItem.getQuantity());
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                //扣除商品庫存
                productDao.updateStock(product.getProductId(),product.getStock()-buyItem.getQuantity());

                //計算總價
                BigDecimal quantity = BigDecimal.valueOf(buyItem.getQuantity());
                BigDecimal amount = quantity.multiply(product.getPrice());
                totalAmount = totalAmount.add(amount);
                //轉換BuyItem to OrderItem
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(buyItem.getProductId());
                orderItem.setQuantity(buyItem.getQuantity());
                orderItem.setAmount(amount);

                orderItemList.add(orderItem);
            }
            //創建訂單
            Integer orderId = orderDao.createOrder(userId, totalAmount);
            orderDao.createOrderItems(orderId, orderItemList);
            return orderId;
        }
    }
