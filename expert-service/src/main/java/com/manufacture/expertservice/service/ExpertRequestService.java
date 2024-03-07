package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.ExpertRequest;

import java.util.List;

public interface ExpertRequestService {
    List<ExpertRequest> findAllRequests();
    List<ExpertRequest> findCompanyRequests(String usercompany_id);
  //  List<ExpertRequest> findSentRequests(Long expert_id);
    ExpertRequest findRequestByID(Long id);
}
