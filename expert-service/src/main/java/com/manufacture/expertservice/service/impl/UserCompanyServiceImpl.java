package com.manufacture.expertservice.service.impl;

import com.manufacture.expertservice.model.UserCompany;
import com.manufacture.expertservice.repository.UserCompanyRepository;
import com.manufacture.expertservice.service.UserCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserCompanyServiceImpl implements UserCompanyService {

    @Autowired
    private UserCompanyRepository userCompanyRepository;

    @Override
    public void addCompany(UserCompany company) {

        userCompanyRepository.save(company);

    }
}
