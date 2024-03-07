package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.model.Company;
import com.manufacture.expertservice.model.TrainingEntity;
import com.manufacture.expertservice.model.UzsakymoForma;
import com.manufacture.expertservice.repository.TrainingEntityRepository;
import com.manufacture.expertservice.service.impl.OrderFormServiceImpl;
import com.manufacture.expertservice.swagger.DescriptionVariables;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@Api(tags = {DescriptionVariables.Order})
@Log4j2
@RestController
@RequestMapping("/api/forms")
public class OrderFormController {

    @Autowired
    private OrderFormServiceImpl orderFormService;

    @Autowired
    private TrainingEntityRepository trainingEntityRepository;

    @GetMapping()
    @ApiOperation(value = "Finds all posted forms data",
            notes = "Returns the entire list of posted forms",
            response = UzsakymoForma.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded", response = UzsakymoForma.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
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
    @ApiOperation(value = "Find the form by id",
            notes = "Provide an id to search the form data in database",
            response = UzsakymoForma.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
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
        TrainingEntity entity = trainingEntityRepository.findTop1ByCompanyOrderByIdDesc(companyid);
        boolean hashExist = false;
        if (entity != null) {
            hashExist = true;
        }
        return hashExist;
    }
}
