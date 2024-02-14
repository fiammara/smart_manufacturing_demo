package com.manufacture.expertservice.controller;


import com.manufacture.expertservice.model.Evaluation;
import com.manufacture.expertservice.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {


    @Autowired
    private EvaluationService evaluationService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Evaluation> findAllEval() {
        return evaluationService.findAllEval();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addEvaluation")
    public String save(@RequestBody @Valid Evaluation eval) {


        evaluationService.addEvaluation(eval);
        return eval.getComment();
    }
}
