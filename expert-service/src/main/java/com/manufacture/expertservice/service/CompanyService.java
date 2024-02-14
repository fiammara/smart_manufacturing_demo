package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.Company;
import com.manufacture.expertservice.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAllComp() {

        return companyRepository.findAll();
    }
}
