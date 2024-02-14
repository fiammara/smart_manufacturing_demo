package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.UserCompany;
import com.manufacture.expertservice.repository.UserCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserCompanyService {

    @Autowired
    private UserCompanyRepository userCompanyRepository;

    public void addCompany(UserCompany company) {

        userCompanyRepository.save(company);

    }
}
