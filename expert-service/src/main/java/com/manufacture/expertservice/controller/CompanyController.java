package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.model.Company;
import com.manufacture.expertservice.service.impl.CompanyServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;

    @GetMapping()
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
