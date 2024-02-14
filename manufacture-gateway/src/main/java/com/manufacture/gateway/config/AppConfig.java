package com.manufacture.gateway.config;

public class AppConfig {
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
