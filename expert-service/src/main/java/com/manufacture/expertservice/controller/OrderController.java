package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.model.Company;
import com.manufacture.expertservice.model.Order;
import com.manufacture.expertservice.service.impl.OrderServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping()
    public ResponseEntity<List<Order>> findAllOrders() {
        log.info("Getting all saved orders");
        List<Order> orderList = orderService.findAllOrders();
        if (orderList.isEmpty()) {
            log.warn("List of orders is empty {}", orderList);
            return ResponseEntity.noContent().build();
        }
        log.debug("Order list size: {}", orderList::size);
        return ResponseEntity.ok(orderList);
    }
}
