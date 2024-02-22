package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrders();
}
