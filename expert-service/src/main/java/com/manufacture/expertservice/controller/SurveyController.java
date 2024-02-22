package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.model.Survey;
import com.manufacture.expertservice.service.impl.SurveyServiceImpl;
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

@Log4j2
@RestController
@RequestMapping("/api/surveys")
public class SurveyController {
    @Autowired
    private SurveyServiceImpl surveyService;


    @GetMapping()
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


    @PostMapping("/users/chooseExpert")
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
}
