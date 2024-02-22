package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.ExpertTest;

import java.util.List;

public interface ExpertTestService {
    List<ExpertTest> findAll();
    void addTest(ExpertTest test);
}
