package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.model.TrainEntity;
import com.manufacture.expertservice.model.UzsakymoForma;
import com.manufacture.expertservice.repository.TrainingEntityRepository;
import com.manufacture.expertservice.service.impl.OrderFormServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/forms")
public class OrderFormController {

    @Autowired
    private OrderFormServiceImpl orderFormService;

    @Autowired
    private TrainingEntityRepository trainingEntityRepository;

    @GetMapping()
    public ResponseEntity<List<UzsakymoForma>> findAllForms() {
        log.info("Getting all registered order forms");
        List<UzsakymoForma> orderFormsList = orderFormService.findAllForms();
        if (orderFormsList.isEmpty()) {
            log.warn("Order forms list is empty {}", orderFormsList);
            return ResponseEntity.noContent().build();
        }
        log.debug("Order forms list size: {}", orderFormsList::size);
        return ResponseEntity.ok(orderFormsList);
    }


    @GetMapping("/form/{formid}")
    public ResponseEntity<UzsakymoForma> findFormById(@PathVariable(value = "formid") String formid) {


        log.info("Find order form by passing form id, where form is :{} ", formid);
        Optional<UzsakymoForma> form = orderFormService.getById(Long.valueOf(formid));
        if (!form.isPresent()) {
            log.warn("Order form with id {} is not found.", formid);
        } else {
            log.debug("Order form with id {} is found: {}", formid, form);
        }
        return form.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/testhash/{companyid}")
    public Boolean hasHash(@PathVariable(value = "companyid") String companyid) {
        TrainEntity entity = trainingEntityRepository.findTop1ByCompanyOrderByIdDesc(companyid);
        boolean hashExist = false;
        if (entity != null) {
            hashExist = true;
        }
        return hashExist;
    }
}
