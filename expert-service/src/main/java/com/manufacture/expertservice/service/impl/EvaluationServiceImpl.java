package com.manufacture.expertservice.service.impl;

import com.manufacture.expertservice.model.Evaluation;
import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.model.UserResponseDTO;
import com.manufacture.expertservice.repository.EvaluationRepository;
import com.manufacture.expertservice.repository.ExpertRequestRepository;
import com.manufacture.expertservice.service.EvaluationService;
//import com.manufacture.identityservice.entity.User;
//import com.manufacture.identityservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private ExpertRequestServiceImpl expertRequestService;
    @Autowired
    private ExpertRequestRepository expertRequestRepository;

    @Autowired
    private RestTemplate template;

    @Override
    public List<Evaluation> findAllEvaluations() {

        return evaluationRepository.findAll();
    }
    @Override
    public Evaluation addEvaluation(Evaluation evaluation) {
        Long longUserId = Long.valueOf(evaluation.getExpertNameId());

        UserResponseDTO expert = template.getForObject("http://IDENTITY-SERVICE/api/users/user/" + longUserId, UserResponseDTO.class);
        assert expert != null;
        String expertName = expert.getName();

        ExpertRequest found = expertRequestService.findRequestByID(1L);

        evaluation.setExpertrequest(found);
        evaluation.setExpertName(expertName);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        evaluation.setSubmit_date(dtf2.format(localDate));

        Set<Evaluation> setOfEvaluations = found.getEvaluations();

        if (setOfEvaluations != null) {

            setOfEvaluations.add(evaluation);
            found.setEvaluations(setOfEvaluations);
        } else {
            Set<Evaluation> newSet = new HashSet<Evaluation>();
            newSet.add(evaluation);
            found.setEvaluations(newSet);
        }
        expertRequestRepository.save(found);
       return evaluation;

    }

}
