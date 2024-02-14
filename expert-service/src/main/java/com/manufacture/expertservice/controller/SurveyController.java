package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.model.Survey;
import com.manufacture.expertservice.model.User;
import com.manufacture.expertservice.model.UzsakymoForma;
import com.manufacture.expertservice.repository.UserRepository;
import com.manufacture.expertservice.repository.UzsakymoFormaRepository;
import com.manufacture.expertservice.service.EmailService;
import com.manufacture.expertservice.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/api")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UzsakymoFormaRepository uzsakymoFormaRepository;


    @Autowired
    EmailService emailService;

    @RequestMapping(method = RequestMethod.GET, value = "/allSurveys")
    public List<Survey> findAllSurveys() {
        return surveyService.findAllSurveys();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addSurvey")
    public long save(@RequestBody @Valid Survey survey) {
        //System.out.println ("Woodarray" + survey.getWoodF());

        StringBuilder technology = new StringBuilder();
        Collections.sort(survey.getTechnologyF());
        for (String c : survey.getTechnologyF()) {
            technology.append(c).append(",");
        }
        survey.setTechnology(technology.toString());


        StringBuilder wood = new StringBuilder();
        Collections.sort(survey.getWoodF());
        for (String o : survey.getWoodF()) {
            wood.append(o).append(",");
            //wood.append("\t");
        }
        survey.setWood(wood.toString());

        StringBuilder metal = new StringBuilder();
        Collections.sort(survey.getMetalF());
        for (Object o : survey.getMetalF()) {
            metal.append(o).append(",");

        }
        survey.setMetal(metal.toString());

        StringBuilder glass = new StringBuilder();
        Collections.sort(survey.getGlassF());
        for (Object o : survey.getGlassF()) {
            glass.append(o).append(",");

        }
        survey.setGlass(glass.toString());

        StringBuilder wiring = new StringBuilder();
        Collections.sort(survey.getWiringF());
        for (Object o : survey.getWiringF()) {
            wiring.append(o).append(",");

        }
        survey.setWiring(wiring.toString());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        System.out.println(dtf.format(localDate));
        survey.setSubmit_date(dtf.format(localDate));

        System.out.println("stringWood" + survey.getWood());
        System.out.println("stringTech" + survey.getTechnology());
        System.out.println("stringglass" + survey.getClass());
        System.out.println("stringwir" + survey.getWiring());
        System.out.println("stringmtl" + survey.getMetal());

        surveyService.addSurvey(survey);

        return survey.getId();
    }


    @PostMapping("/users/chooseExpert")
    public String save(@RequestBody @Valid ExpertRequest expertRequest) {
        //System.out.println(expertRequest.getUser().toString());

        Set<User> expertlist = new HashSet<User>();
        System.out.println(expertRequest.getSelectedOptions().toString());
        System.out.println(expertRequest.getText());
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

        //emailService.sendSimpleMessage("fiammara@gmail.com", "Sumani gamyba ekspertams", expertRequest.getText());
        //System.out.println (expertRequest.getUser().toString());


        String userid = expertRequest.getCompanyid();
        Long longuserid = Long.valueOf(userid);
        //UserCompany company=usercompanyRepository.findById(longuserid).get();
        User company = userRepository.findById(longuserid).get();
        UzsakymoForma forma = uzsakymoFormaRepository.findTop1ByCompanyidOrderByIdDesc(userid);
        //System.out.println("forma" + forma.getId());
        expertRequest.setUser(company);
        expertRequest.setExperts(expertlist);
        expertRequest.setLastFormToEvaluateId(forma.getId());

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        expertRequest.setSubmit_date(dtf2.format(localDate));

        surveyService.addExpertRequest(expertRequest);

        return expertRequest.getText();
    }
}
