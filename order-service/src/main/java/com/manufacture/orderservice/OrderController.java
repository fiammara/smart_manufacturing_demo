package com.manufacture.orderservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/order")
    public ResponseEntity<String> getTestString() {
        log.info("Getting all registered companies");
        String stringForTest = "order service controller";

        return ResponseEntity.ok(stringForTest);
    }
}
