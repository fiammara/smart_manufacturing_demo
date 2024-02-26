package com.manufacture.orderservice.service.impl;


import com.manufacture.orderservice.model.Order;
import com.manufacture.orderservice.repository.OrderRepository;
import com.manufacture.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAllOrders() {

        return orderRepository.findAll();
    }
}

