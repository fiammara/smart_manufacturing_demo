package com.manufacture.expertservice.service.impl;

import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.model.Survey;
import com.manufacture.expertservice.model.User;
import com.manufacture.expertservice.model.UzsakymoForma;
import com.manufacture.expertservice.repository.ExpertRequestRepository;
import com.manufacture.expertservice.repository.SurveyRepository;
import com.manufacture.expertservice.repository.UserRepository;
import com.manufacture.expertservice.repository.OrderFormRepository;
import com.manufacture.expertservice.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private ExpertRequestRepository expertRequestRepository;

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderFormRepository uzsakymoFormaRepository;

    @Override
    public List<Survey> findAllSurveys() {

        return surveyRepository.findAll();
    }

    @Override
    public Survey addSurvey(Survey survey) {
        survey.setTechnology(setTech(survey.getTechnologyF()));
        survey.setWood(setTech(survey.getWoodF()));
        survey.setMetal(setTech(survey.getMetalF()));
        survey.setGlass(setTech(survey.getGlassF()));
        survey.setWiring(setTech(survey.getWiringF()));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        survey.setSubmit_date(dtf.format(localDate));
        return surveyRepository.save(survey);

    }
    private String setTech(ArrayList<String> arrayList) {
        StringBuilder technology = new StringBuilder();
        Collections.sort(arrayList);
        for (String c : arrayList) {
            technology.append(c).append(",");
        }
        return technology.toString();
    }
    @Override
    public ExpertRequest addExpertRequest(ExpertRequest expertRequest) {
        Set<User> expertlist = new HashSet<User>();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(expertRequest.getSelectedOptions().toString());

        List<Long> list = new ArrayList<Long>();

        while (matcher.find()) {
            list.add(Long.parseLong(matcher.group()));
        }

        List<User> listu = userRepository.findByIdIn(list);
        for (User u : listu) {
            System.out.println(u.getEmail());
            expertlist.add(u);
        }

        String userid = expertRequest.getCompanyid();
        Long longuserid = Long.valueOf(userid);
        User company = userRepository.findById(longuserid).get();
        UzsakymoForma forma = uzsakymoFormaRepository.findTop1ByCompanyidOrderByIdDesc(userid);
        expertRequest.setUser(company);
        expertRequest.setExperts(expertlist);
        expertRequest.setLastFormToEvaluateId(forma.getId());

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        expertRequest.setSubmit_date(dtf2.format(localDate));
        return expertRequestRepository.save(expertRequest);

    }
}
