package com.manufacture.expertservice.controller;


import com.manufacture.expertservice.model.Evaluation;
import com.manufacture.expertservice.service.impl.EvaluationServiceImpl;
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
@RequestMapping("/api/evaluations")
public class EvaluationController {


    @Autowired
    private EvaluationServiceImpl evaluationService;


    @GetMapping()
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
