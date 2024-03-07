package com.manufacture.expertservice.service.impl;


import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.repository.ExpertRequestRepository;
import com.manufacture.expertservice.service.ExpertRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExpertRequestServiceImpl implements ExpertRequestService {

    @Autowired
    private ExpertRequestRepository expertRequestRepository;

    @Override
    public List<ExpertRequest> findAllRequests() {

        return expertRequestRepository.findAll();
    }
    @Override
    public List<ExpertRequest> findCompanyRequests(String usercompany_id) {

        return expertRequestRepository.findByCompanyid(usercompany_id);
    }
   /* @Override
    public List<ExpertRequest> findSentRequests(Long expert_id) {
        User expertX=userRepository.findById(Long.valueOf(expert_id)).get();
        	expertX.getRequest()

        List<ExpertRequest> listfiltered =
            expertRequestRepository.findReqByExpertId(expert_id);
        expertRequestRepository.findByExperts_Requestid(expert_id);
        	Set<User> experts = new HashSet<User>();

        	List<ExpertRequest> ofrequests = new ArrayList<ExpertRequest>();

        	List<ExpertRequest> list= userRepository.g

        for (ExpertRequest req: listall) {

        	experts.add(req.getExperts());
        }
        return listfiltered;
    } */

    @Override
    public ExpertRequest findRequestByID(Long id) {


        return expertRequestRepository.findByIdOrError(id);
    }
}
