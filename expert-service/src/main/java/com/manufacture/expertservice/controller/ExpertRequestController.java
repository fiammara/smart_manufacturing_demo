package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.service.ExpertRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/exprequests")
public class ExpertRequestController {
    @Autowired
    private ExpertRequestService expertRequestService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ExpertRequest> findAllReq() {
        return expertRequestService.findAllReq();
    }

    @GetMapping("/all/{usercompany_id}")
    public List<ExpertRequest> findCompReq(@PathVariable(value = "usercompany_id") String usercompany_id) {
        return expertRequestService.findCompanyRequests(usercompany_id);
    }

    @GetMapping("/all/exp/{expert_id}")
    public List<ExpertRequest> findSentReq(@PathVariable(value = "expert_id") String expert_id) {
        Long longuserid = Long.valueOf(expert_id);
        return expertRequestService.findSentRequests(longuserid);
    }
}
