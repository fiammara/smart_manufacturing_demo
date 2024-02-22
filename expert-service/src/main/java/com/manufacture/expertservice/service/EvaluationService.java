package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.Evaluation;

import java.util.List;

public interface EvaluationService {
    List<Evaluation> findAllEvaluations();
    Evaluation addEvaluation(Evaluation eval);
}
