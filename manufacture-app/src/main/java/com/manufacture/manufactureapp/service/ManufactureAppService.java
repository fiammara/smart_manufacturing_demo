package com.manufacture.manufactureapp.service;

import com.manufacture.manufactureapp.client.OrderServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufactureAppService {

    @Autowired
    private OrderServiceClient orderServiceClient;

    public String greeting() {
        return "Welcome to Manufacture App Microservice";
    }

    public String checkOrderStatus() {

        return orderServiceClient.fetchOrderStatus();
    }
}
