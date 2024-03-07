package com.manufacture.expertservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.manufacture.expertservice.model.Company;
import com.manufacture.expertservice.model.Evaluation;
import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.model.Survey;
import com.manufacture.expertservice.model.TopicEvent;
import com.manufacture.expertservice.service.impl.SurveyServiceImpl;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {DescriptionVariables.SURVEY})
@Log4j2
@RestController
@RequestMapping("/api/surveys")
public class SurveyController {
    @Autowired
    private SurveyServiceImpl surveyService;
    @Autowired
    private RequestProducer requestProducer;


    @GetMapping()
    @ApiOperation(value = "Finds all posted surveys",
            notes = "Returns the entire list of posted surveys",
            response = Survey.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded", response = Survey.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Survey>> findAllSurveys() {
        log.info("Getting all surveys");
        List<Survey> surveyList = surveyService.findAllSurveys();
        if (surveyList.isEmpty()) {
            log.warn("Survey list is empty {}", surveyList);
            return ResponseEntity.noContent().build();
        }
        log.debug("Survey list size: {}", surveyList::size);
        return ResponseEntity.ok(surveyList);
    }

    @PostMapping()
    @ApiOperation(value = "Saves the survey data to the database",
            response = Survey.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HTMLResponseMessages.HTTP_200),
            @ApiResponse(code = 400, message = HTMLResponseMessages.HTTP_400),
            @ApiResponse(code = 404, message = HTMLResponseMessages.HTTP_404),
            @ApiResponse(code = 500, message = HTMLResponseMessages.HTTP_500)})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Survey> addSurvey(
            @Valid @RequestBody Survey survey,
            BindingResult bindingResult) {
        log.info("Saving new survey : {}", survey);
        if (bindingResult.hasErrors()) {
            log.error("New survey was not saved, error: {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }
        Survey surveySaved = surveyService.addSurvey(survey);
        log.debug("New survey is saved: {}", survey);
        return new ResponseEntity<>(surveySaved, HttpStatus.CREATED);
    }


    @PostMapping("/users/choose/expert")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ExpertRequest> saveRequestForExpert(@RequestBody @Valid ExpertRequest expertRequest, BindingResult bindingResult) {
        log.info("Saving new expert request : {}", expertRequest);
        if (bindingResult.hasErrors()) {
            log.error("New expert request was not saved, error: {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }
        ExpertRequest expertRequestSaved = surveyService.addExpertRequest(expertRequest);
        return new ResponseEntity<>(expertRequestSaved, HttpStatus.CREATED);
    }

    @PostMapping("/users/choose/expert/test")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveRequestTopicForExpert(@RequestBody @Valid ExpertRequest expertRequest, BindingResult bindingResult) throws JsonProcessingException {
        log.info("Saving new expert request : {}", expertRequest);
        if (bindingResult.hasErrors()) {
            log.error("New expert request was not saved, error: {}", bindingResult);
            return null;
        }

        requestProducer.sendMessage(expertRequest);
        return "Request placed successfully";
    }
}
