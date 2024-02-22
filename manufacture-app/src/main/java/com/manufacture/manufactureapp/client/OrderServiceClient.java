package com.manufacture.manufactureapp.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderServiceClient {
    @Autowired
    private RestTemplate template;

    public String fetchOrderStatus() {
        return template.getForObject("http://ORDER-SERVICE/orders/order" , String.class);
    }
}
