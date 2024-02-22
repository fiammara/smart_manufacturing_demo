package com.manufacture.manufactureapp.controller;


import com.manufacture.manufactureapp.service.ManufactureAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/manufacture")
    public class ManufactureAppController {

        @Autowired
        private ManufactureAppService service;

        @GetMapping("/home")
        public String greetingMessage() {
            return service.greeting();
        }

        @GetMapping("/testorder")
        public String checkOrderStatus() {
            return service.checkOrderStatus();
        }
}
