package com.manufacture.expertservice.service.impl;

import com.manufacture.expertservice.model.Evaluation;
import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.model.User;
import com.manufacture.expertservice.repository.EvaluationRepository;
import com.manufacture.expertservice.repository.ExpertRequestRepository;
import com.manufacture.expertservice.repository.UserRepository;
import com.manufacture.expertservice.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private UserRepository userRepository;

    @Override
    public List<Evaluation> findAllEvaluations() {

        return evaluationRepository.findAll();
    }
    @Override
    public Evaluation addEvaluation(Evaluation eval) {
        Long longuserid = Long.valueOf(eval.getExpertNameId());
        User expert = userRepository.getOne(longuserid);
        String expertName = expert.getName();


        ExpertRequest found = expertRequestService.findRequestByID(1L);

        eval.setExpertrequest(found);
        eval.setExpertName(expertName);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        eval.setSubmit_date(dtf2.format(localDate));

        Set<Evaluation> setOfEv = found.getEvaluations();

        if (setOfEv != null) {

            setOfEv.add(eval);
            found.setEvaluations(setOfEv);
        } else {
            Set<Evaluation> newSet = new HashSet<Evaluation>();
            newSet.add(eval);
            found.setEvaluations(newSet);
        }
        expertRequestRepository.save(found);
       return eval;

    }

}
