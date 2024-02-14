package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.Order;
import com.manufacture.expertservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAllOrders() {

        return orderRepository.findAll();
    }
}

