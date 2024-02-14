package com.manufacture.expertservice.service;


import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.repository.ExpertRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExpertRequestService {

    @Autowired
    private ExpertRequestRepository expertRequestRepository;


    public List<ExpertRequest> findAllReq() {

        return expertRequestRepository.findAll();
    }

    public List<ExpertRequest> findCompanyRequests(String usercompany_id) {

        return expertRequestRepository.findByCompanyid(usercompany_id);
    }

    public List<ExpertRequest> findSentRequests(Long expert_id) {
        //User expertX=userRepository.findById(Long.valueOf(expert_id)).get();
        //	expertX.getRequest()

        List<ExpertRequest> listfiltered =
            expertRequestRepository.findReqByExpertId(expert_id);
        //expertRequestRepository.findByExperts_Requestid(expert_id);
        //	Set<User> experts = new HashSet<User>();

        //	List<ExpertRequest> ofrequests = new ArrayList<ExpertRequest>();

        //	List<ExpertRequest> list= userRepository.g

        //for (ExpertRequest req: listall) {

        //	experts.add(req.getExperts());
        //}
        return listfiltered;
    }

    public ExpertRequest findRequestByID(Long id) {


        return expertRequestRepository.findByIdOrError(id);
    }
}
