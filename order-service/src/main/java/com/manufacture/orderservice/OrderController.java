package com.manufacture.orderservice;

import com.manufacture.orderservice.model.Order;
import com.manufacture.orderservice.service.impl.OrderServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;


    @GetMapping("/order")
    public ResponseEntity<String> getTestString() {
        log.info("Getting test orders");
        String stringForTest = "Welcome from order service controller";

        return ResponseEntity.ok(stringForTest);
    }


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
