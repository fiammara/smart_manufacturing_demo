package com.manufacture.expertservice.controller;


import com.manufacture.expertservice.message.ResponseMessage;
import com.manufacture.expertservice.model.ExpertTest;
import com.manufacture.expertservice.service.impl.ExpertTestServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/tests")
public class ExpertTestController {
    @Autowired
    private ExpertTestServiceImpl expertTestService;

    @GetMapping()
    public ResponseEntity<List<ExpertTest>> findAllTests() {
        log.info("Getting all expert tests");
        List<ExpertTest> testList = expertTestService.findAll();
        if (testList.isEmpty()) {
            log.warn("Test list is empty {}", testList);
            return ResponseEntity.noContent().build();
        }
        log.debug("Test list size: {}", testList::size);
        return ResponseEntity.ok(testList);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> saveTestData(@RequestBody @Valid ExpertTest test) {

        String message = "";
        try {
            expertTestService.addTest(test);
            message = "Test data was uploaded successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Error, test data could not be saved";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }

}
