package com.manufacture.expertservice.controller;


import com.manufacture.expertservice.message.ResponseMessage;
import com.manufacture.expertservice.model.ExpertTest;
import com.manufacture.expertservice.service.ExpertTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ExpertTestController {
    @Autowired
    private ExpertTestService expertTestService;


    @RequestMapping(method = RequestMethod.GET, value = "/allTests")
    public List<ExpertTest> findAll() {
        return expertTestService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTest")
    public ResponseEntity<?> save(@RequestBody @Valid ExpertTest test) {

        String message = "";
        try {
            expertTestService.addTest(test);
            message = "Duomenys sėkmingai pateikti";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Klaida";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }

}
