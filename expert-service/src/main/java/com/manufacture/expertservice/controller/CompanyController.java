package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.model.Company;
import com.manufacture.expertservice.service.impl.CompanyServiceImpl;
import com.manufacture.expertservice.swagger.DescriptionVariables;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = {DescriptionVariables.COMPANY})
@Log4j2
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;

    @GetMapping()
    @ApiOperation(value = "Finds all registered companies",
            notes = "Returns the entire list of registered companies",
            response = Company.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded", response = Company.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<List<Company>> findAllCompanies() {
        log.info("Getting all registered companies");
        List<Company> companyList = companyService.findAllCompanies();
        if (companyList.isEmpty()) {
            log.warn("Company list is empty {}", companyList);
            return ResponseEntity.noContent().build();
        }
        log.debug("Companies list size: {}", companyList::size);
        return ResponseEntity.ok(companyList);
    }
}
