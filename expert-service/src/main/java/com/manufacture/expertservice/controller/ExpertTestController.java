package com.manufacture.expertservice.controller;


import com.manufacture.expertservice.message.ResponseMessage;
import com.manufacture.expertservice.model.Evaluation;
import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.model.ExpertTest;
import com.manufacture.expertservice.service.impl.ExpertTestServiceImpl;
import com.manufacture.expertservice.swagger.DescriptionVariables;
import com.manufacture.expertservice.swagger.HTMLResponseMessages;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(tags = {DescriptionVariables.ExpertTest})
@Log4j2
@RestController
@RequestMapping("/api/tests")
public class ExpertTestController {
    @Autowired
    private ExpertTestServiceImpl expertTestService;

    @GetMapping()
    @ApiOperation(value = "Finds all expert tests",
            notes = "Returns the entire list of expert tests",
            response = ExpertTest.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded", response = ExpertTest.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
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
    @ApiOperation(value = "Saves the expert test form data to the database",
            response = Evaluation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HTMLResponseMessages.HTTP_200),
            @ApiResponse(code = 400, message = HTMLResponseMessages.HTTP_400),
            @ApiResponse(code = 404, message = HTMLResponseMessages.HTTP_404),
            @ApiResponse(code = 500, message = HTMLResponseMessages.HTTP_500)})
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
