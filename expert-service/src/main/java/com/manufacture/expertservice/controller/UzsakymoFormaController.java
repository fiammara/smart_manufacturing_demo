package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.model.TrainEntity;
import com.manufacture.expertservice.model.UzsakymoForma;
import com.manufacture.expertservice.repository.TrainEntityRepository;
import com.manufacture.expertservice.service.UzsakymoFormaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/forms")
public class UzsakymoFormaController {

    @Autowired
    private UzsakymoFormaService uzsakymoFormaService;

    @Autowired
    private TrainEntityRepository trainEntityRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<UzsakymoForma> findAllForms() {
        return uzsakymoFormaService.findAllForms();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/form")
    public UzsakymoForma findbyId(@RequestBody String userid) {
        return uzsakymoFormaService.getById(Long.valueOf(userid));

    }

    @GetMapping("/form/{formid}")
    public UzsakymoForma findFormbyId(@PathVariable(value = "formid") String formid) {

        UzsakymoForma form = uzsakymoFormaService.getById(Long.valueOf(formid));

        return form;
    }


    @GetMapping("/testhash/{companyid}")
    public Boolean hasHash(@PathVariable(value = "companyid") String companyid) {
        TrainEntity entity = trainEntityRepository.findTop1ByCompanyOrderByIdDesc(companyid);
        Boolean hashexist = false;
        if (entity != null) {
            hashexist = true;
        } else {
            hashexist = false;
        }
        ;


        return hashexist;
    }
}
