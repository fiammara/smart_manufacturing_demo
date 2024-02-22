package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.model.Survey;

import java.util.List;

public interface SurveyService {
    List<Survey> findAllSurveys();

    Survey addSurvey(Survey survey);

    ExpertRequest addExpertRequest(ExpertRequest request);
}
