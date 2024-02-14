package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.model.Survey;
import com.manufacture.expertservice.repository.ExpertRequestRepository;
import com.manufacture.expertservice.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SurveyService {

    @Autowired
    private ExpertRequestRepository expertRequestRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    public List<Survey> findAllSurveys() {

        return surveyRepository.findAll();
    }

    public void addSurvey(Survey survey) {

        surveyRepository.save(survey);

    }

    public void addExpertRequest(ExpertRequest request) {

        expertRequestRepository.save(request);

    }
}
