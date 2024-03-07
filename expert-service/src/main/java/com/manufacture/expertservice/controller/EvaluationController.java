package com.manufacture.expertservice.controller;


import com.manufacture.expertservice.model.Company;
import com.manufacture.expertservice.model.Evaluation;
import com.manufacture.expertservice.service.impl.EvaluationServiceImpl;
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

@Api(tags = {DescriptionVariables.EVALUATION})
@Log4j2
@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {


    @Autowired
    private EvaluationServiceImpl evaluationService;


    @GetMapping()
    @ApiOperation(value = "Finds all sent evaluations",
            notes = "Returns the entire list of registered evaluations",
            response = Evaluation.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded", response = Evaluation.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Evaluation>> findAllEvaluations() {
        log.info("Getting all evaluations provided by experts");
        List<Evaluation> evaluationList = evaluationService.findAllEvaluations();
        if (evaluationList.isEmpty()) {
            log.warn("List of evaluations is empty {}", evaluationList);
            return ResponseEntity.noContent().build();
        }
        log.debug("List of evaluations, size: {}", evaluationList::size);
        return ResponseEntity.ok(evaluationList);
    }
    @PostMapping()
    @ApiOperation(value = "Saves the evaluation to the database",
            response = Evaluation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HTMLResponseMessages.HTTP_200),
            @ApiResponse(code = 400, message = HTMLResponseMessages.HTTP_400),
            @ApiResponse(code = 404, message = HTMLResponseMessages.HTTP_404),
            @ApiResponse(code = 500, message = HTMLResponseMessages.HTTP_500)})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Evaluation> addEvaluation(
            @Valid @RequestBody Evaluation evaluation,
            BindingResult bindingResult) throws Exception {
        log.info("Saving new evaluation : {}", evaluation);
        if (bindingResult.hasErrors()) {
            log.error("New evaluation was not saved, error: {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }
        Evaluation evaluationSaved = evaluationService.addEvaluation(evaluation);
        log.debug("New evaluation is saved: {}", evaluation);
        return new ResponseEntity<>(evaluationSaved, HttpStatus.CREATED);
    }
}
