package com.manufacture.expertservice.service.impl;

import com.manufacture.expertservice.model.Order;
import com.manufacture.expertservice.repository.OrderRepository;
import com.manufacture.expertservice.service.OrderService;
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

