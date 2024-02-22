package com.manufacture.expertservice.service.impl;

import com.manufacture.expertservice.model.Company;
import com.manufacture.expertservice.repository.CompanyRepository;
import com.manufacture.expertservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> findAllCompanies() {

        return companyRepository.findAll();
    }
}
