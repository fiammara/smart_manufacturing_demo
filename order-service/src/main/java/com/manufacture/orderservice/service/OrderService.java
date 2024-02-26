package com.manufacture.orderservice.service;



import com.manufacture.orderservice.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrders();
}
